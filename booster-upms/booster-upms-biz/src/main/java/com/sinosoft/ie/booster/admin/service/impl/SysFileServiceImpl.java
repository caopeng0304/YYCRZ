package com.sinosoft.ie.booster.admin.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysFileEntity;
import com.sinosoft.ie.booster.admin.mapper.SysFileMapper;
import com.sinosoft.ie.booster.admin.service.SysFileService;
import com.sinosoft.ie.booster.common.core.constant.CommonConstants;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.minio.service.MinioTemplate;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件管理
 *
 * @author Luckly
 * @since 2019-06-18 17:18:42
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFileEntity> implements SysFileService {
	private final MinioTemplate minioTemplate;


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

		try {
			minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, file.getInputStream());
			//文件管理数据记录,收集管理追踪文件
			fileLog(file, fileName);
		} catch (Exception e) {
			log.error("上传失败", e);
			return R.failed(e.getLocalizedMessage());
		}
		return R.ok(resultMap);
	}

	/**
	 * 读取文件
	 *
	 * @param fileName
	 * @param response
	 */
	@Override
	public void getFile(String fileName, HttpServletResponse response) {
		int separator = fileName.lastIndexOf(StrUtil.DASHED);
		try (InputStream inputStream = minioTemplate.getObject(fileName.substring(0, separator),
				fileName.substring(separator + 1))) {
			response.setContentType("application/octet-stream; charset=UTF-8");
			IoUtil.copy(inputStream, response.getOutputStream());
		} catch (Exception e) {
			log.error("文件读取异常", e);
		}
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
	public Boolean deleteFile(Long id) {
		SysFileEntity file = this.getById(id);
		minioTemplate.removeObject(CommonConstants.BUCKET_NAME, file.getFileName());
		return this.removeById(id);
	}

	/**
	 * 文件管理数据记录,收集管理追踪文件
	 *
	 * @param file     上传文件格式
	 * @param fileName 文件名
	 */
	private void fileLog(MultipartFile file, String fileName) {
		SysFileEntity sysFile = new SysFileEntity();
		//原文件名
		String original = file.getOriginalFilename();
		sysFile.setFileName(fileName);
		sysFile.setOriginal(original);
		sysFile.setFileSize(file.getSize());
		sysFile.setType(FileUtil.extName(original));
		sysFile.setBucketName(CommonConstants.BUCKET_NAME);
		this.save(sysFile);
	}

}
