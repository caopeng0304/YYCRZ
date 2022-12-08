package com.sinosoft.ie.booster.visualdev.util;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author booster开发平台组
 * @since 2021/3/16 10:47
 */
@Data
@Component
public class ConfigValueUtil {

	/**
	 * 静态资源根目录
	 */
	private String path = "static-resources/";
	/**
	 * 临时文件存储路径
	 */
	private String temporaryFilePath = "TemporaryFile";
	/**
	 * 系统文件存储路径
	 */
	private String systemFilePath = "SystemFile";
	/**
	 * 文件模板存储路径
	 */
	private String templateFilePath = "TemplateFile";
	/**
	 * 邮件文件存储路径
	 */
	private String emailFilePath = "EmailFile";
	/**
	 * 大屏图片存储目录
	 */
	private String biVisualPath = "BiVisualPath";
	/**
	 * 用户头像存储路径
	 */
	private String userAvatarFilePath = "UserAvatar";
	/**
	 * IM聊天图片+语音存储路径
	 */
	private String imContentFilePath = "IMContentFile";
	/**
	 * 允许上传文件类型
	 */
	private String allowUploadFileType = "jpg,gif,png,bmp,jpeg,doc,docx,ppt,pptx,xls,xlsx,pdf,txt,rar,zip,csv";
	/**
	 * 代码生成器临时目录
	 */
	private String codeTempPath = "CodeTemp";
	/**
	 * 前端附件文件目录
	 */
	private String webAnnexFilePath = "WebAnnexFile";

	public String getTemporaryFilePath() {
		return path + temporaryFilePath + File.separator;
	}

	public String getSystemFilePath() {
		return path + systemFilePath + File.separator;
	}

	public String getTemplateFilePath() {
		return path + templateFilePath + File.separator;
	}

	public String getEmailFilePath() {
		return path + emailFilePath + File.separator;
	}

	public String getUserAvatarFilePath() {
		return path + userAvatarFilePath + File.separator;
	}

	public String getImContentFilePath() {
		return path + imContentFilePath + File.separator;
	}

	public String getBiVisualPath() {
		return path + biVisualPath + File.separator;
	}

	public String getCodeTempPath() {
		return path + codeTempPath + File.separator;
	}

	public String getWebAnnexFilePath() {
		return path + webAnnexFilePath + File.separator;
	}
}
