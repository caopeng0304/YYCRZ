package com.sinosoft.ie.booster.common.encrypt.encrypt;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.symmetric.SM4;
import com.sinosoft.ie.booster.common.encrypt.annotation.Algorithm;

import java.nio.charset.StandardCharsets;

/**
 * @author 段鹏宇
 */
public class DefaultEncryptor {

	// 公钥
	final static String PUBLICKEY = "046BB57C1815D9D4D939A64976D32DEBFFE9433C6BA222BCADBA5FFCFCF1072B660DDE8040DB6E291E3A70F6971F084E315D75E6DB3AAA659D8AE64715DD14AC7A";
	// 秘钥
	final static String PRIVATEKEY = "00CF6306EF758162D59D588A1CFB62698813A4C2D595650AE29F5126BC50BEB255";
	// SM4 Key
	final static String SM4KEY = "booster1booster2";


	public static String encrypt(Algorithm var1, String var2) {
		return commonCrypt(var1, var2, true);
	}


	public static String decrypt(Algorithm var1, String var2) {
		return commonCrypt(var1, var2, false);
	}

	/**
	 * 加减密算法
	 *
	 * @param var1  算法类型
	 * @param var2
	 * @param state true 加密; false 解密
	 * @return
	 */
	public static String commonCrypt(Algorithm var1, String var2, boolean state) {
		// 公钥加密, 私钥解密
		if (var1 == Algorithm.SM2) {
			// 自定义秘钥
			SM2 sm2 = SmUtil.sm2(PRIVATEKEY, PUBLICKEY);
			return state ? sm2.encryptBase64(var2, KeyType.PublicKey) : sm2.decryptStr(var2, KeyType.PrivateKey);
		} else if (var1 == Algorithm.SM3) {
			return state ? SmUtil.sm3(var2) : var2;
		} else {
			// 国密算法SM4
			SM4 sm4 = SmUtil.sm4(SM4KEY.getBytes(StandardCharsets.UTF_8));
			return state ? sm4.encryptBase64(var2) : sm4.decryptStr(var2);
		}
	}

	public static void main(String[] args) {
		String sm2EncStr = DefaultEncryptor.encrypt(Algorithm.SM2, "hello world!");
		System.out.println(sm2EncStr);
		System.out.println(DefaultEncryptor.decrypt(Algorithm.SM2, sm2EncStr));

		System.out.println(DefaultEncryptor.encrypt(Algorithm.SM3, "hello world!"));

		String sm4EncStr = DefaultEncryptor.encrypt(Algorithm.SM4, "hello world!");
		System.out.println(sm4EncStr);
		System.out.println(DefaultEncryptor.decrypt(Algorithm.SM4, sm4EncStr));
	}
}
