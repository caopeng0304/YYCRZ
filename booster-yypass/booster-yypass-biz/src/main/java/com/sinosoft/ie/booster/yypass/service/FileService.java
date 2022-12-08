package com.sinosoft.ie.booster.yypass.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.yypass.entity.FileEntity;
import com.sinosoft.ie.booster.yypass.entity.PassFileEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
public interface FileService extends IService<FileEntity> {

    List<FileEntity> getList(FileEntity fileEntity);

	FileEntity getInfo(String id);

    void delete(FileEntity entity);

    boolean update(String id, FileEntity entity);

	/**
	 * 上传文件
	 *
	 * @param file
	 * @return
	 */
	R<Map<String, String>> uploadFile(MultipartFile file);

	R<Map<String, String>> uploadImageFile(MultipartFile file);

	Map<String, String> ExceluploadFile(byte[] bytes,InputStream inputStream);

	/**
	 * 读取文件
	 *
	 * @param fileName
	 * @param response
	 */
	void getFile(String fileName, HttpServletResponse response);

	String getURL(String fileName);

	/**
	 * 删除文件
	 *
	 * @param id
	 * @return
	 */
	Boolean deleteFile(String id);


	public void onlinePreview(String url, HttpServletResponse response) throws Exception;

	String encodeBase64String(FileEntity fe);

}
