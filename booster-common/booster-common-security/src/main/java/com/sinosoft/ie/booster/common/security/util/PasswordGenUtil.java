package com.sinosoft.ie.booster.common.security.util;

/**
 * 初始密码生成工具
 *
 * @author haocy
 */
public class PasswordGenUtil {

	public static String gen(int pwdLen) {
		String[] pwdStr = {
				"qwertyuiopasdfghjklzxcvbnm",
				"QWERTYUIOPASDFGHJKLZXCVBNM",
				"0123456789",
				"_"
		};

		char[] chs = new char[pwdLen];
		for (int i = 0; i < pwdStr.length; i++) {
			int idx = (int) (Math.random() * pwdStr[i].length());
			chs[i] = pwdStr[i].charAt(idx);
		}

		for (int i = pwdStr.length; i < pwdLen; i++) {
			int arrIdx = (int) (Math.random() * pwdStr.length);
			int strIdx = (int) (Math.random() * pwdStr[arrIdx].length());
			chs[i] = pwdStr[arrIdx].charAt(strIdx);
		}

		for (int i = 0; i < 1000; i++) {
			int idx1 = (int) (Math.random() * chs.length);
			int idx2 = (int) (Math.random() * chs.length);
			if (idx1 == idx2) {
				continue;
			}
			char tempChar = chs[idx1];
			chs[idx1] = chs[idx2];
			chs[idx2] = tempChar;
		}

		return new String(chs);
	}

	public static void main(String[] args) {
		System.out.println(PasswordGenUtil.gen(12));
	}
}
