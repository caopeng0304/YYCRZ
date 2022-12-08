package com.sinosoft.ie.booster.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.admin.api.entity.SysFileEntity;
import com.sinosoft.ie.booster.common.core.util.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 文件管理
 *
 * @author Luckly
 * @since 2019-06-18 17:18:42
 */
public interface SysFileService extends IService<SysFileEntity> {

	/**
	 * 上传文件
	 *
	 * @param file
	 * @return
	 */
	R<Map<String, String>> uploadFile(MultipartFile file);

	/**
	 * 读取文件
	 *
	 * @param fileName
	 * @param response
	 */
	void getFile(String fileName, HttpServletResponse response);

	/**
	 * 删除文件
	 *
	 * @param id
	 * @return
	 */
	Boolean deleteFile(Long id);
}
