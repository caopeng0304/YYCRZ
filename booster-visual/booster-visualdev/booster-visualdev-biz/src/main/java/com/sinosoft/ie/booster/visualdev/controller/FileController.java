package com.sinosoft.ie.booster.visualdev.controller;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.DownloadVO;
import com.sinosoft.ie.booster.common.core.model.UploaderVO;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.constant.enums.FileTypeEnum;
import com.sinosoft.ie.booster.visualdev.util.*;
import com.sinosoft.ie.booster.visualdev.util.file.FileSizeUtil;
import com.sinosoft.ie.booster.visualdev.util.file.OptimizeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.charset.Charset;

/**
 * 通用控制器
 *
 * @author booster开发平台组
 * @since 2021-03-23
 */
@Api(tags = "文件处理" , value = "file" )
@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private ConfigValueUtil configValueUtil;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private FileManageUtil fileManageUtil;

	/**
	 * 上传文件/图片
	 *
	 * @return
	 */
	@ApiOperation("上传文件/图片" )
	@PostMapping("/Uploader/{type}" )
	public R<UploaderVO> uploader(@PathVariable("type" ) String type, MultipartFile file) {
		String fileType = UpUtil.getFileType(file);
		//验证类型
		if (!OptimizeUtil.fileType(configValueUtil.getAllowUploadFileType(), fileType)) {
			return R.failed("上传失败，文件格式不允许上传" );
		}
		if (OptimizeUtil.fileSize(file.getSize(), FileSizeUtil.WEXINFILESIZE)) {
			return R.failed("上传失败，文件大小超过1M" );
		}
		String fileName = DateUtil.dateNow("yyyyMMdd" ) + "_" + RandomUtil.uuId() + "." + fileType;
		UploaderVO vo = UploaderVO.builder().name(fileName).build();
		String filePath = fileManageUtil.getFilePath(type);
		switch (type) {
			case FileTypeEnum.USERAVATAR:
				vo.setUrl(UploaderUtil.uploaderImg(fileName));
				break;
			case FileTypeEnum.ANNEX:
				BoosterUser userInfo = SecurityUtils.getUser();
				vo.setUrl(UploaderUtil.uploaderFile("/file/Download/", userInfo.getId() + "#" + fileName));
				break;
			case FileTypeEnum.ANNEXPIC:
				vo.setUrl(UploaderUtil.uploaderImg("/file/Image/annex/", fileName));
				break;
			case FileTypeEnum.WORKFLOW:
				vo.setUrl(UploaderUtil.uploaderImg("/file/Image/annex/", fileName));
				break;
		}
		FileUtil.upFile(file, filePath, fileName);
		return R.ok(vo);
	}

	/**
	 * 获取下载文件链接
	 *
	 * @return
	 */
	@ApiOperation("获取下载文件链接" )
	@GetMapping("/Download/{type}/{fileName}" )
	public R<DownloadVO> downloadUrl(@PathVariable("type" ) String type, @PathVariable("fileName" ) String fileName) {
		BoosterUser userInfo = SecurityUtils.getUser();
		String filePath = fileManageUtil.getFilePath(type) + fileName;
		if (FileUtil.fileIsFile(filePath)) {
			DownloadVO vo = DownloadVO.builder().name(fileName).url(UploaderUtil.uploaderFile(userInfo.getId() + "#" + fileName + "#" + type)).build();
			return R.ok(vo);
		}
		return R.failed("文件不存在" );
	}

	/**
	 * 下载文件链接
	 *
	 * @return
	 */
	@ApiOperation("下载文件链接" )
	@GetMapping("/Download" )
	public void downloadFile() throws DataException {
		HttpServletRequest request = ServletUtil.getRequest();
		assert request != null;
		String reqJson = request.getParameter("encryption" );
		String fileNameAll = DesUtil.aesDecode(reqJson);
		if (!StrUtil.isEmpty(fileNameAll)) {
			String[] data = fileNameAll.split("#" );
			String fileName = data.length > 1 ? data[1] : "";
			String type = data.length > 2 ? data[2] : "";
			String filePath = fileManageUtil.getFilePath(type.toLowerCase()) + fileName;
			System.out.println(Charset.defaultCharset() + "路径1是" + filePath);
			DownUtil.dowloadFile(filePath, fileName);
		}
	}

	/**
	 * 下载文件链接
	 *
	 * @return
	 */
	@ApiOperation("下载模板文件链接" )
	@GetMapping("/DownloadModel" )
	public void downloadModel() {
		HttpServletRequest request = ServletUtil.getRequest();
		String reqJson = request.getParameter("encryption" );
		String fileNameAll = DesUtil.aesDecode(reqJson);
		if (!StrUtil.isEmpty(fileNameAll)) {
			String token = fileNameAll.split("#" )[0];
			if (redisUtil.exists(token)) {
				String fileName = fileNameAll.split("#" )[1];
				String filePath = configValueUtil.getTemplateFilePath() + fileName;
				System.out.println(Charset.defaultCharset() + "路径2是" + filePath);
				DownUtil.dowloadFile(filePath, fileName);
			}
		}
	}

	/**
	 * 获取图片
	 *
	 * @param fileName
	 * @param type
	 * @return
	 */
	@ApiOperation("获取图片" )
	@GetMapping("/Image/{type}/{fileName}" )
	public void downLoadImg(@PathVariable("type" ) String type, @PathVariable("fileName" ) String fileName) {
		String filePath = fileManageUtil.getFilePath(type) + fileName;
		File file = new File(filePath);
		if (file.exists()) {
			DownUtil.dowloadFile(filePath);
		}
	}

	/**
	 * 获取IM聊天图片
	 * 注意 后缀名前端故意把 .替换@
	 *
	 * @param fileName
	 * @return
	 */
	@ApiOperation("获取IM聊天图片" )
	@GetMapping("/IMImage/{fileName}" )
	public void imImage(@PathVariable("fileName" ) String fileName) {
		String paths = configValueUtil.getImContentFilePath() + fileName;
		File file = new File(paths);
		if (file.exists()) {
			DownUtil.dowloadFile(paths);
		}
	}

	/**
	 * 获取IM聊天语音
	 * 注意 后缀名前端故意把 .替换@
	 *
	 * @param fileName
	 * @return
	 */
	@ApiOperation("获取IM聊天语音" )
	@GetMapping("/IMVoice/{fileName}" )
	public void imVoice(@PathVariable("fileName" ) String fileName) {
		String paths = configValueUtil.getImContentFilePath() + fileName.replaceAll("@" , "." );
		File file = new File(paths);
		if (file.exists()) {
			DownUtil.dowloadFile(paths);
		}
	}

	/**
	 * 通过type获取路径
	 *
	 * @param type 类型
	 * @return
	 */
	@GetMapping("/getPath/{type}" )
	public String getPath(@PathVariable("type" ) String type) {
		return fileManageUtil.getFilePath(type);
	}

}
