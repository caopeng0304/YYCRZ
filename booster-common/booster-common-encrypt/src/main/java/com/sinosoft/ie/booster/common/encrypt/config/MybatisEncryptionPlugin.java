package com.sinosoft.ie.booster.common.encrypt.config;

import com.sinosoft.ie.booster.common.encrypt.annotation.Algorithm;
import com.sinosoft.ie.booster.common.encrypt.annotation.FieldEncrypt;
import com.sinosoft.ie.booster.common.encrypt.encrypt.DefaultEncryptor;
import com.sinosoft.ie.booster.common.encrypt.util.Util;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author 段鹏宇
 */
@Intercepts({
		@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class MybatisEncryptionPlugin implements Interceptor {

	private static final Logger log = LoggerFactory.getLogger(MybatisEncryptionPlugin.class);

	/**
	 * mybatis拦截器
	 *
	 * @param invocation
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		// args[0] 为 MappedStatement 数据
		MappedStatement ms = (MappedStatement) args[0];
		// 获取 sqlCommandType ---------> (提交类型: insert/update )
		SqlCommandType sqlCommandType = ms.getSqlCommandType();
		// 获取对象数据 ---------------> args[1]
		Object parameter = args[1];
		log.info("object: " + parameter);
		if (Util.encryptionRequired(parameter, sqlCommandType)) {
			// 判断参数是map还是String
			if (parameter instanceof MapperMethod.ParamMap) {
				//noinspection unchecked
				MapperMethod.ParamMap<Object> paramMap = (MapperMethod.ParamMap<Object>) parameter;
				encryptParamMap(paramMap);
			} else {
				encryptEntity(parameter);
			}
		}
		return invocation.proceed();
	}

	private void encryptEntity(Object parameter) throws MybatisCryptoException {
		processFields(EncryptorProvider.get(parameter.getClass()), parameter);
	}

	private void encryptParamMap(MapperMethod.ParamMap<Object> paramMap) throws MybatisCryptoException {
		Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();
		for (Map.Entry<String, Object> entry : entrySet) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value == null || key == null) {
				continue;
			}
			if (value instanceof ArrayList) {
				//noinspection rawtypes
				ArrayList list = (ArrayList) value;
				if (!list.isEmpty()) {
					Object firstItem = list.get(0);
					Class<?> itemClass = firstItem.getClass();
					Set<Field> encryptedFields = EncryptorProvider.get(itemClass);
					for (Object item : list) {
						processFields(encryptedFields, item);
					}
				}
			} else {
				processFields(EncryptorProvider.get(value.getClass()), value);
			}
		}
	}

	private void processFields(Set<Field> encryptedFields, Object entry) throws MybatisCryptoException {
		if (encryptedFields == null || encryptedFields.isEmpty()) {
			return;
		}
		// 遍历Field
		for (Field field : encryptedFields) {
			// 获取注解: FieldEncrypt 通过 field.getAnnotation(FieldEncrypt.class)
			FieldEncrypt fieldEncrypt = field.getAnnotation(FieldEncrypt.class);
			if (fieldEncrypt == null) {
				continue;
			}
			try {
				field.setAccessible(true);
				Object originalVal = field.get(entry);
				if (originalVal == null) {
					continue;
				}
				// 获取field的注解对应的Enum的类型
				Algorithm algorithm = fieldEncrypt.algorithm();
				// 调用加密方法 DefaultEncryptor---------->加减密实现类
				String encryptedVal = DefaultEncryptor.encrypt(algorithm, originalVal.toString());
				field.set(entry, encryptedVal);
			} catch (Exception e) {
				throw new MybatisCryptoException(e);
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
