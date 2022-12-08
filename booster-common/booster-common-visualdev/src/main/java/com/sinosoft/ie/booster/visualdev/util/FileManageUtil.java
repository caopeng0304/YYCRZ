package com.sinosoft.ie.booster.visualdev.util;

import com.sinosoft.ie.booster.common.core.model.FileModel;
import com.sinosoft.ie.booster.visualdev.constant.enums.FileTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 上传附件处理
 *
 * @author booster开发平台组
 * @since 2021/3/15 11:55
 */
@Component
public class FileManageUtil {

	@Autowired
	private ConfigValueUtil configValueUtil;

	/**
	 * 通过fileType获取文件夹名称
	 *
	 * @param fileType 文件类型
	 * @return
	 */
	public String getFilePath(String fileType) {
		String filePath = null;
		//判断是那种类型得到相应的文件夹
		switch (fileType){
			//用户头像存储路径
			case FileTypeEnum.USERAVATAR:
				filePath = configValueUtil.getUserAvatarFilePath();
				break;
			//邮件文件存储路径
			case FileTypeEnum.MAIL:
				filePath = configValueUtil.getEmailFilePath();
				break;
			//前端附件文件目录
			case FileTypeEnum.ANNEX:
			case FileTypeEnum.ANNEXPIC:
				filePath = configValueUtil.getWebAnnexFilePath();
				break;
			//IM聊天图片+语音存储路径
			case FileTypeEnum.IM:
				filePath = configValueUtil.getImContentFilePath();
				break;
			//临时文件存储路径
			case FileTypeEnum.WORKFLOW:
				filePath = configValueUtil.getSystemFilePath();
				break;
			//临时文件存储路径
			case FileTypeEnum.TEMPORARY:
				filePath = configValueUtil.getTemporaryFilePath();
				break;
			//允许上传文件类型
			case FileTypeEnum.ALLOWUPLOADFILETYPE:
				filePath = configValueUtil.getAllowUploadFileType();
				break;
			//文件模板存储路径
			case FileTypeEnum.TEMPLATEFILE:
				filePath = configValueUtil.getTemplateFilePath();
				break;
			default:
				break;
		}
		return filePath;
	}

	// 添加附件：将临时文件夹的文件拷贝到正式文件夹里面

	/**
	 * 添加附件：将临时文件夹的文件拷贝到正式文件夹里面
	 *
	 * @param data list集合
	 */
	public void createFile(List<FileModel> data) {
		if (data != null && data.size() > 0) {
			String temporaryFilePath = getFilePath(FileTypeEnum.TEMPORARY);
			String systemFilePath = getFilePath(FileTypeEnum.WORKFLOW);
			for (FileModel item : data) {
				FileUtil.copyFile(temporaryFilePath + item.getFileId(), systemFilePath + item.getFileId());
			}
		}
	}

	/**
	 * 更新附件
	 *
	 * @param data list集合
	 */
	public void updateFile(List<FileModel> data) {
		if (data != null && data.size() > 0) {
			String temporaryFilePath = getFilePath(FileTypeEnum.TEMPORARY);
			String systemFilePath = getFilePath(FileTypeEnum.WORKFLOW);
			for (FileModel item : data) {
				if ("add".equals(item.getFileType())) {
					FileUtil.copyFile(temporaryFilePath + item.getFileId(), systemFilePath + item.getFileId());
				} else if ("delete".equals(item.getFileType())) {
					FileUtil.deleteFile(systemFilePath + item.getFileId());
				}
			}
		}
	}

	/**
	 * 删除附件
	 *
	 * @param data list集合
	 */
	public void deleteFile(List<FileModel> data) {
		if (data != null && data.size() > 0) {
			String systemFilePath = getFilePath(FileTypeEnum.WORKFLOW);
			for (FileModel item : data) {
				FileUtil.deleteFile(systemFilePath + item.getFileId());
			}
		}
	}
}
