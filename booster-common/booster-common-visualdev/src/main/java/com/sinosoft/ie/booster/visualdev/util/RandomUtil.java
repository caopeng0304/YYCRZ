package com.sinosoft.ie.booster.visualdev.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author booster开发平台组
 * @since 2021/3/16 10:51
 */
public class RandomUtil {

	/**
	 * 生成主键id
	 *
	 * @return
	 */
	public static String uuId() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	}

	/**
	 * 生成6位数随机英文
	 *
	 * @return
	 */
	public static String enUuId() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			//你想生bai成几个字符的，du就把3改成zhi几，dao如果改成１,那就生成一个1653随机字母．
			str.append((char) (Math.random() * 26 + 'a'));
		}
		return str.toString();
	}

	/**
	 * 生成排序编码
	 *
	 * @return
	 */
	public static Integer parses() {
		return 0;
	}

	/**
	 * 生成短信验证码
	 *
	 * @return
	 */
	public static String getRandomCode() {
		StringBuilder code = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < 6; i++) {
			int ran = rand.nextInt(10);
			code.append(ran);
		}
		return code.toString();
	}
}
