package ok.practice.encryption;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JaysptTest {

	@Autowired
	StringEncryptor stringEncryptor;

	@Test
	public void encryptPwd() {
		String result = stringEncryptor.encrypt("password");
		System.out.println(result);
	}
}
