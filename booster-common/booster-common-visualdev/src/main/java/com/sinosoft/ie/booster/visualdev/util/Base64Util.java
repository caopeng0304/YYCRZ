package com.sinosoft.ie.booster.visualdev.util;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author booster开发平台组
 * @since 2021/3/16 10:45
 */
public class Base64Util {

	/**
	 * 把文件转化为base64.
	 *
	 * @param filePath 源文件路径
	 */
	public static String fileToBase64(String filePath) {
		if (!StrUtil.isEmpty(filePath)) {
			try {
				byte[] bytes = Files.readAllBytes(Paths.get(filePath));
				return Base64.encodeBase64String(bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * base64转化为文件.
	 *
	 * @param base64   base64
	 * @param filePath 目标文件路径
	 */
	public static void base64ToFile(String base64, String filePath) {
		try {
			Files.write(Paths.get(filePath), Base64.decodeBase64(base64), StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
