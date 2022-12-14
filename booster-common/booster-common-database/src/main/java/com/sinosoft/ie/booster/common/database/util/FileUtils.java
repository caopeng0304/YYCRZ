package com.sinosoft.ie.booster.common.database.util;

import java.io.File;

/**
 * 文件相关工具
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/4/3 18:46
 */
public class FileUtils {
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");

	/**
	 * 获取文件路径，根据系统进行处理
	 *
	 * @param path {@link String}
	 * @return {@link String}
	 */
	public static String getRealFilePath(String path) {
		return path.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);
	}

	/**
	 * 获取全路径中的文件名
	 *
	 * @param file 文件
	 * @return 文件名
	 */
	public static String getFileName(File file) {
		if (file == null) {
			return null;
		}
		return getFileName(file.getPath());
	}

	/**
	 * 获取全路径中的文件名
	 *
	 * @param filePath 文件路径
	 * @return 文件名
	 */
	public static String getFileName(String filePath) {
		if (FILE_SEPARATOR.equals(filePath)) {
			return filePath;
		}
		int lastSep = filePath.lastIndexOf(File.separator);
		return lastSep == -1 ? filePath : filePath.substring(lastSep + 1);
	}

	/**
	 * 根据文件路径获取文件
	 *
	 * @param filePath 文件路径
	 * @return 文件
	 */
	public static File getFileByPath(String filePath) {
		return File.separator.equals(filePath) ? null : new File(filePath);
	}

	/**
	 * 判断文件是否存在
	 *
	 * @param filePath 文件路径
	 * @return {@code true}: 存在<br>{@code false}: 不存在
	 */
	public static boolean isFileExists(String filePath) {
		return isFileExists(getFileByPath(filePath));
	}

	/**
	 * 判断文件是否存在
	 *
	 * @param file 文件
	 * @return {@code true}: 存在<br>{@code false}: 不存在
	 */
	public static boolean isFileExists(File file) {
		return file != null && file.exists();
	}
}
