package com.sinosoft.ie.booster.common.encrypt.config;

import com.sinosoft.ie.booster.common.encrypt.annotation.Algorithm;
import com.sinosoft.ie.booster.common.encrypt.annotation.FieldEncrypt;
import com.sinosoft.ie.booster.common.encrypt.encrypt.DefaultEncryptor;
import com.sinosoft.ie.booster.common.encrypt.util.Util;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

/**
 * @author dpy
 */
@Intercepts({
		@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
public class MybatisDecryptionPlugin implements Interceptor {

	private static final Logger log = LoggerFactory.getLogger(MybatisDecryptionPlugin.class);

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object result = invocation.proceed();
		if (result == null) {
			return null;
		}
		if (result instanceof ArrayList) {
			//noinspection rawtypes
			ArrayList resultList = (ArrayList) result;
			if (resultList.isEmpty()) {
				return result;
			}
			Object firstItem = resultList.get(0);
			boolean needToDecrypt = Util.decryptionRequired(firstItem);
			if (!needToDecrypt) {
				return result;
			}
			Set<Field> encryptedFields = EncryptorProvider.get(firstItem.getClass());
			if (encryptedFields == null || encryptedFields.isEmpty()) {
				return result;
			}
			for (Object item : resultList) {
				decryptEntity(encryptedFields, item);
			}
		} else {
			if (Util.decryptionRequired(result)) {
				decryptEntity(EncryptorProvider.get(result.getClass()), result);
			}
		}
		return result;
	}

	private void decryptEntity(Set<Field> encryptedFields, Object item) throws MybatisCryptoException {
		if (encryptedFields == null || encryptedFields.isEmpty()) {
			return;
		}
		for (Field field : encryptedFields) {
			FieldEncrypt fieldEncrypt = field.getAnnotation(FieldEncrypt.class);
			if (fieldEncrypt != null) {
				try {
					field.setAccessible(true);
					Object originalVal = field.get(item);
					if (originalVal != null) {
						Algorithm algorithm = fieldEncrypt.algorithm();
						String decryptedVal = DefaultEncryptor.decrypt(algorithm, originalVal.toString());
						field.set(item, decryptedVal);
					}
				} catch (Exception e) {
					throw new MybatisCryptoException(e);
				}
			}
		}
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}
}
