package com.sinosoft.ie.booster.auth.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.sinosoft.ie.booster.common.core.constant.CommonConstants;
import com.sinosoft.ie.booster.auth.exception.BoosterAuth2Exception;
import lombok.SneakyThrows;

/**
 * OAuth2 异常格式化
 *
 * @author lengleng
 * @since 2019/2/1
 */
public class BoosterAuth2ExceptionSerializer extends StdSerializer<BoosterAuth2Exception> {

	public BoosterAuth2ExceptionSerializer() {
		super(BoosterAuth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(BoosterAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeObjectField("code", CommonConstants.FAIL);
		gen.writeStringField("msg", value.getMessage());
		gen.writeStringField("data", value.getErrorCode());
		// 资源服务器会读取这个字段
		gen.writeStringField("error", value.getMessage());
		gen.writeEndObject();
	}

}
