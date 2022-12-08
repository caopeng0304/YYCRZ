import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AESTest {

	private static final String KEY_ALGORITHM = "AES";

	private static String decrypt(String data, String rootPass) {
		AES aes = new AES(Mode.CBC, Padding.NoPadding, new SecretKeySpec(rootPass.getBytes(), KEY_ALGORITHM),
				new IvParameterSpec(rootPass.getBytes()));
		byte[] result = aes.decrypt(Base64.decode(data.getBytes(StandardCharsets.UTF_8)));
		return new String(result, StandardCharsets.UTF_8);
	}

	private static String encrypt(String data, String rootPass) {
		AES aes = new AES(Mode.CBC, Padding.ZeroPadding, rootPass.getBytes(), rootPass.getBytes());
		byte[] result = aes.encrypt(data.getBytes(StandardCharsets.UTF_8));
		return Base64.encode(result);
	}

	public static void main(String[] args) {
		String rootPass = "hi,booster-cloud";
		System.out.println(encrypt("123456", rootPass));
		System.out.println(decrypt("wva11G7ohz6hLjzehmnO+Q==", rootPass).trim());
	}
}
