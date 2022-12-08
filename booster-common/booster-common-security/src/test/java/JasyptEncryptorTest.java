import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.core.env.StandardEnvironment;

public class JasyptEncryptorTest {
	public static void testEnvironmentProperties() {
		System.setProperty("jasypt.encryptor.password", "booster");
		StringEncryptor stringEncryptor = new DefaultLazyEncryptor(new StandardEnvironment());

		//加密方法
		System.out.println(stringEncryptor.encrypt("gen"));
		//解密方法
		System.out.println(stringEncryptor.decrypt("DLsOPy5gmUspuinqWkwpqA=="));
	}

	public static void main(String[] args) {
		testEnvironmentProperties();
	}

}
