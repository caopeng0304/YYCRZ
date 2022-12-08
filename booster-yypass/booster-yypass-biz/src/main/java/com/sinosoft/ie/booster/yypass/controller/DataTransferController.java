package com.sinosoft.ie.booster.yypass.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteDeptService;
import com.sinosoft.ie.booster.admin.api.feign.RemoteUserService;
import com.sinosoft.ie.booster.admin.api.model.UserDTO;
import com.sinosoft.ie.booster.common.core.constant.CommonConstants;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.minio.service.MinioTemplate;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;
import com.sinosoft.ie.booster.yypass.entity.*;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.*;
import com.sinosoft.ie.booster.yypass.service.*;
import com.sinosoft.ie.booster.yypass.util.SmsSendAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.beans.binding.LongExpression;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * aps_system
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-08-08 14:06:39
 */
@Slf4j
@RestController
@Api(tags = "pass_basic_info")
@RequestMapping("/dataTransfer")
public class DataTransferController {

	@Autowired
	PassBasicInfoService passBasicInfoService;
	@Autowired
	PassLogService passLogService;
	@Autowired
	PassDelayService passDelayService;
	@Autowired
	PassFileService passFileService;
	@Autowired
	RemoteUserService remoteUserService;
	@Autowired
	NoPassBasicInfoService noPassBasicInfoService;
	@Autowired
	FileService fileService;
	@Autowired
	SendMsgLogService sendMsgLogService;
	@Autowired
	DataTransferService dataTransferService;

	@Autowired
	private MinioTemplate minioTemplate;

	@Autowired
	private RemoteDeptService remoteDeptService;

	@Value("${passcardCertifiedFile}")
	private String passcardCertifiedFile;
	@Value("${passCardPic}")
	private String passCardPic;
	@Value("${roles}")
	private String roles;

	/**
	 * 导入用户
	 */
	@PostMapping("import-dataTransfer")
	@ApiOperation(value = "导入数据", notes = "传入excel")
	public R<Object> importPassbasicinfo(MultipartFile file) {
		String filename = file.getOriginalFilename();
		if (org.springframework.util.StringUtils.isEmpty(filename)) {
			throw new RuntimeException("请上传文件!");
		}
		if ((!org.springframework.util.StringUtils.endsWithIgnoreCase(filename, ".xls") && !org.springframework.util.StringUtils.endsWithIgnoreCase(filename, ".xlsx"))) {
			throw new RuntimeException("请上传正确的excel文件!");
		}
		InputStream inputStream;
		try {
			DataTransferImportListener importListener = new DataTransferImportListener(dataTransferService);
			inputStream = new BufferedInputStream(file.getInputStream());
			ExcelReaderBuilder builder = EasyExcel.read(inputStream, DataTransferExcelVO.class, importListener);
			builder.doReadAll();
		} catch (IOException e) {
			e.printStackTrace();
			return R.failed(null, "操作失败");
		}
		return R.ok(null, "操作成功");
	}



	@PostMapping("file-dataTransfer")
	@ApiOperation(value = "文件迁移", notes = "文件迁移")
	public R<Object> FilePassbasicinfo() {
		try {
			QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();
			queryWrapper.eq("is_delete","4");
			List<PassBasicInfoEntity> list = passBasicInfoService.list(queryWrapper);
			if (list != null && list.size() > 0){
			for (PassBasicInfoEntity entity : list){
				String applyBatch = entity.getApplyBatch();
				String card_number = entity.getCardNumber();
				String year = applyBatch.substring(0,4);
				// 保存照片
				savePic(entity,card_number);
				// 保存附件
				saveFile(entity,applyBatch,year);
			}

			}
			return R.ok(null, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(null, "操作失败");
		}
	}

	@PostMapping("save-system-dataTransfer")
	@ApiOperation(value = "迁移过来的数据添加到系统组织中去", notes = "迁移过来的数据添加到系统组织中去")
	public R<Object> saveSystemDataTransfer() {
		try {
			QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();
			queryWrapper.eq("is_delete","4");
			List<PassBasicInfoEntity> list = passBasicInfoService.list(queryWrapper);
			if (list != null && list.size() > 0){
				for (PassBasicInfoEntity entity : list){
					// 获取系统组织
					R<SysDeptEntity>  r = remoteDeptService.infoByName(entity.getUnitName());
					SysDeptEntity sysDeptEntity = r.getData();
					if (sysDeptEntity != null ){
					   Long deptId = sysDeptEntity.getDeptId();
					   List<Long> role = new ArrayList();
						role.add(Long.parseLong(roles));
						UserDTO userDTO = new UserDTO();
						userDTO.setUsername(entity.getIdCard());
						userDTO.setPhone(entity.getPhone());
						userDTO.setRole(role);
						userDTO.setDeptId(deptId);
						userDTO.setManager(entity.getNames());
						R<Boolean> booleanR = remoteUserService.synUser(userDTO);
					}
				}

			}
			return R.ok(null, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(null, "操作失败");
		}
	}

	public void savePic(PassBasicInfoEntity entity,String card_number){
		try {
			String path = passCardPic+card_number+".jpg";
			File localfile = new File(path);
			String id = RandomUtil.uuId();
			String fileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(card_number+".jpg");
			MultipartFile file = getMultipartFile(localfile);
			minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, file.getInputStream(),file.getInputStream().available(),file.getContentType());
			FileEntity sysFile = new FileEntity();
			//保存文件
			String original = card_number+".jpg";
			sysFile.setId(id);
			sysFile.setFileName(fileName);
			sysFile.setOriginal(original);
			sysFile.setFileSize(null);
			sysFile.setType(FileUtil.extName(original));
			sysFile.setBucketName(CommonConstants.BUCKET_NAME);
			sysFile.setDelFlag("0");
			this.fileService.save(sysFile);
			// 保存关联关系
			PassFileEntity fileEntity = new PassFileEntity();
			fileEntity.setId(RandomUtil.uuId());
			fileEntity.setFileId(id);
			fileEntity.setPassBasicInfoId(entity.getId());
			fileEntity.setFileType("A");
			fileEntity.setIsDelete("1");
			this.passFileService.create(fileEntity);
		}catch (Exception e){
			System.out.println(e);
		}
	}


	public void saveFile(PassBasicInfoEntity entity,String applyBatch,String year){
		try{
		String path = passcardCertifiedFile +  year + "\\"+ applyBatch;
		File localfile = new File(path);
		File[] files = localfile.listFiles();
		List<String> fileNames = new ArrayList<>();
		for (File f : files) {
			if (f.isDirectory()) {
				System.out.println("这是一个文件夹");
			} else {
				fileNames.add(f.getName());
				// 添加附件


			}
		}
		if (fileNames != null && fileNames.size() > 0){
			for (String filename :fileNames){
				String id = RandomUtil.uuId();
				String fileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(filename);
				File local_file = new File(path+"\\"+filename);
				MultipartFile file = getMultipartFile(local_file);
				minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, file.getInputStream(),file.getInputStream().available(),file.getContentType());
				FileEntity sysFile = new FileEntity();
				//保存文件
				String original = filename;
				sysFile.setId(id);
				sysFile.setFileName(fileName);
				sysFile.setOriginal(original);
				sysFile.setFileSize(null);
				sysFile.setType(FileUtil.extName(original));
				sysFile.setBucketName(CommonConstants.BUCKET_NAME);
				sysFile.setDelFlag("0");
				this.fileService.save(sysFile);
				// 保存关联关系
				PassFileEntity fileEntity = new PassFileEntity();
				fileEntity.setId(RandomUtil.uuId());
				fileEntity.setFileId(id);
				fileEntity.setPassBasicInfoId(entity.getId());
				fileEntity.setFileType("B");
				fileEntity.setIsDelete("1");
				this.passFileService.create(fileEntity);
			}
		}
		}catch (Exception e){
			System.out.println(e);
		}
	}


	public static MultipartFile getMultipartFile(File file) {
		DiskFileItem item = new DiskFileItem("file"
				, MediaType.MULTIPART_FORM_DATA_VALUE
				, true
				, file.getName()
				, (int)file.length()
				, file.getParentFile());
		try {
			OutputStream os = item.getOutputStream();
			os.write(FileUtils.readFileToByteArray(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new CommonsMultipartFile(item);
	}






}
