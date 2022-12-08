package com.sinosoft.ie.booster.yypass.service.impl;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysFileEntity;
import com.sinosoft.ie.booster.common.core.constant.CommonConstants;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.minio.service.MinioTemplate;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;
import com.sinosoft.ie.booster.yypass.entity.FileEntity;
import com.sinosoft.ie.booster.yypass.entity.PassBasicInfoEntity;
import com.sinosoft.ie.booster.yypass.entity.PassFileEntity;
import com.sinosoft.ie.booster.yypass.mapper.FileMapper;
import com.sinosoft.ie.booster.yypass.service.FileService;
import com.sinosoft.ie.booster.yypass.util.FileConvertUtil;
import com.sinosoft.ie.booster.yypass.util.ImageUtil;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper,FileEntity> implements FileService {

    @Autowired
	FileMapper fileMapper;

    @Autowired
	private MinioTemplate minioTemplate;

	/**
	 * 上传文件
	 *
	 * @param file
	 * @return
	 */
	@Override
	public R<Map<String, String>> uploadFile(MultipartFile file) {
		String fileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file.getOriginalFilename());
		Map<String, String> resultMap = new HashMap<>(4);
		resultMap.put("bucketName", CommonConstants.BUCKET_NAME);
		resultMap.put("fileName", fileName);

		try {minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, file.getInputStream(),file.getInputStream().available(),file.getContentType());


			//文件管理数据记录,收集管理追踪文件
			FileEntity fileEntity = fileLog(file, fileName);
			resultMap.put("original",fileEntity.getOriginal());
			resultMap.put("id",fileEntity.getId());
		} catch (Exception e) {
			log.error("上传失败", e);
			return R.failed(e.getLocalizedMessage());
		}
		return R.ok(resultMap);
	}


	@Override
	public R<Map<String, String>> uploadImageFile(MultipartFile file) {
		String fileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file.getOriginalFilename());
		Map<String, String> resultMap = new HashMap<>(4);
		resultMap.put("bucketName", CommonConstants.BUCKET_NAME);
		resultMap.put("fileName", fileName);
		try {
			InputStream inputStream = ImageUtil.changeFile(file);
			System.out.println(inputStream.available());
			System.out.println(file.getContentType());
			/*minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, file.getInputStream(),
					file.getInputStream().available(),file.getContentType());*/
			minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, inputStream,
					inputStream.available(),file.getContentType());

			//文件管理数据记录,收集管理追踪文件
			FileEntity fileEntity = fileLog(file, fileName);
			resultMap.put("original",fileEntity.getOriginal());
			resultMap.put("id",fileEntity.getId());
		} catch (Exception e) {
			log.error("上传失败", e);
			return R.failed(e.getLocalizedMessage());
		}
		return R.ok(resultMap);
	}

	public Map<String, String> ExceluploadFile(byte[] bytes,InputStream inputStream) {
		String id = IdUtil.simpleUUID();
		String fileName = id + StrUtil.DOT + "png";
		Map<String, String> resultMap = new HashMap<>(4);
		resultMap.put("bucketName", CommonConstants.BUCKET_NAME);
		resultMap.put("fileName", fileName);

		try {
            InputStream input = ImageUtil.changeFileBytes(bytes);
			minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, input,input.available(),"image/png");

			//文件管理数据记录,收集管理追踪文件
			FileEntity sysFile = new FileEntity();
			//原文件名
			sysFile.setId(id);
			sysFile.setFileName(fileName);
			sysFile.setOriginal(fileName);
			sysFile.setBucketName(CommonConstants.BUCKET_NAME);
			sysFile.setDelFlag("0");
			this.save(sysFile);
			resultMap.put("original",fileName);
			resultMap.put("id",id);
		} catch (Exception e) {
			log.error("上传失败", e);
			return null;
		}
		return resultMap;
	}

	/**
	 * 读取文件
	 *
	 * @param fileName
	 * @param response
	 */
	@Override
	public void getFile(String fileName, HttpServletResponse response) {
		List<FileEntity> list = fileMapper.getFile(fileName);
		int separator = fileName.lastIndexOf(StrUtil.DASHED);
		/*try (InputStream inputStream = minioTemplate.getObject(fileName.substring(0, separator),
				fileName.substring(separator + 1))) {*/
		try (InputStream inputStream = minioTemplate.getObject(CommonConstants.BUCKET_NAME,
				fileName)) {
			response.setContentType("application/octet-stream; charset=UTF-8");
            if (list != null && list.size() > 0){
            	String name = list.get(0).getOriginal();
				response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(name, "utf-8"));
			}
			IoUtil.copy(inputStream, response.getOutputStream());
		} catch (Exception e) {
			log.error("文件读取异常", e);
		}
	}

	public String getURL(String fileName){

		return minioTemplate.getObjectURL(CommonConstants.BUCKET_NAME, fileName, 100000);
	}

	/**
	 * 删除文件
	 *
	 * @param id
	 * @return
	 */
	@Override
	@SneakyThrows
	@Transactional(rollbackFor = Exception.class)
	public Boolean deleteFile(String id) {
		FileEntity file = this.getById(id);
		minioTemplate.removeObject(CommonConstants.BUCKET_NAME, file.getFileName());
		return this.removeById(id);
	}

	/**
	 * 文件管理数据记录,收集管理追踪文件
	 *
	 * @param file     上传文件格式
	 * @param fileName 文件名
	 */
	private FileEntity fileLog(MultipartFile file, String fileName) {
		FileEntity sysFile = new FileEntity();
		//原文件名
		String original = file.getOriginalFilename();
		sysFile.setId(RandomUtil.uuId());
		sysFile.setFileName(fileName);
		sysFile.setOriginal(original);
		sysFile.setFileSize(file.getSize());
		sysFile.setType(FileUtil.extName(original));
		sysFile.setBucketName(CommonConstants.BUCKET_NAME);
		sysFile.setDelFlag("0");
		this.save(sysFile);
		return sysFile;
	}
	@Override
	public List<FileEntity> getList(FileEntity fileEntity) {
		QueryWrapper<FileEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.setEntity(fileEntity);
		return this.fileMapper.selectList(queryWrapper);
	}

	@Override
	public FileEntity getInfo(String id) {
		QueryWrapper<FileEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().eq(FileEntity::getId,id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void delete(FileEntity entity) {

	}


	@Override
	public boolean update(String id, FileEntity entity) {
		return false;
	}

	/**  * @Description:系统文件在线预览接口  * @Author: tarzan  */
	public void onlinePreview(String url, HttpServletResponse response) throws Exception {
		//获取文件类型
		String[] str = FileConvertUtil.split(url,"\\.");

		if(str.length==0){
			throw new Exception("文件格式不正确");
		}
		String suffix = str[str.length-1];
		/*if(!suffix.equals("txt") && !suffix.equals("doc") && !suffix.equals("docx") && !suffix.equals("xls")
				&& !suffix.equals("xlsx") && !suffix.equals("ppt") && !suffix.equals("pptx")){
			throw new Exception("文件格式不支持预览");
		}*/
		InputStream in= FileConvertUtil.convertNetFiles(minioTemplate,url,suffix);
		OutputStream outputStream = response.getOutputStream();
		//创建存放文件内容的数组
		byte[] buff =new byte[1024];
		//所读取的内容使用n来接收
		int n;
		//当没有读取完时,继续读取,循环
		while((n=in.read(buff))!=-1){
			//将字节数组的数据全部写入到输出流中
			outputStream.write(buff,0,n);
		}
		//强制将缓存区的数据进行输出
		outputStream.flush();
		//关流
		outputStream.close();
		in.close();
	}

	public String encodeBase64String(FileEntity fe){
		byte[] data = null;
		try (InputStream inputStream = minioTemplate.getObject(CommonConstants.BUCKET_NAME, fe.getFileName())) {
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
                        byte[] buff = new byte[100];
                        int rc = 0;
                        while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                           swapStream.write(buff, 0, rc);
                          }
                          data = swapStream.toByteArray();
			return Base64.encodeBase64String(data);
		} catch (Exception e) {
			log.error("文件读取异常", e);
			return null;
		}
	}
}