package com.sinosoft.ie.booster.yypass.controller;


import cn.hutool.core.io.IoUtil;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.yypass.entity.FileEntity;
import com.sinosoft.ie.booster.yypass.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * aps_system
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-08-08 14:06:39
 */
@Slf4j
@RestController
@Api(tags = "file")
@RequestMapping("/File")
public class FileController {

	@Autowired
	FileService fileService;

	/**
	 * 上传
	 *
	 * @param
	 * @return
	 */
	@PostMapping("/uploadFile")
	@Transactional
	public R uploadFile(@RequestParam("file") MultipartFile file) throws DataException {
		return this.fileService.uploadFile(file);
	}

	@PostMapping("/uploadImageFile")
	@Transactional
	public R uploadImageFile(@RequestParam("file") MultipartFile file) throws DataException {
		return this.fileService.uploadImageFile(file);
	}



	/**
	 * 下载
	 *
	 * @param
	 * @return
	 */
	@GetMapping("/downFile")
	public void downFile(String fileName, HttpServletResponse response){
	       this.fileService.getFile(fileName,response);
	}

	@GetMapping("/getURL")
	public String getURL(String fileName){
		return fileService.getURL(fileName);
	}

	@GetMapping("/getBase64")
	public String getBase64(String fileName){
		FileEntity fe = new FileEntity();
		fe.setFileName(fileName);
		String encodeString = fileService.encodeBase64String(fe);
		System.out.println(encodeString);
		return encodeString;
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	@Transactional
	public R delete(@PathVariable("id") String id){
		Boolean b = this.fileService.deleteFile(id);
		return R.ok(b);
	}

	/**
	 * 获取本地文件
	 *
	 * @param fileName 文件名称
	 * @param response 本地文件
	 */
	@SneakyThrows
	@GetMapping("/local-file/{fileName}")
	public void localFile(@PathVariable String fileName, HttpServletResponse response) {
		ClassPathResource resource = new ClassPathResource("file/" + fileName);
		response.setContentType("application/octet-stream; charset=UTF-8");
		IoUtil.copy(resource.getInputStream(), response.getOutputStream());
	}


	@ApiOperation(value = "系统文件在线预览接口")
	@PostMapping("/api/file/onlinePreview")
	public void onlinePreview(@RequestParam("url") String url, HttpServletResponse response) throws Exception{
		fileService.onlinePreview(url,response);
	}


}
