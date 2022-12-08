package com.sinosoft.ie.booster.common.encrypt.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.sinosoft.ie.booster.common.encrypt.annotation.FieldSensitive;
import com.sinosoft.ie.booster.common.encrypt.annotation.SensitiveType;
import com.sinosoft.ie.booster.common.encrypt.util.DesensitizedUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Objects;

/**
 * @author 段鹏宇
 */
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveSerializer extends JsonSerializer<String> implements ContextualSerializer {

	private Integer prefixNoMaskLen;

	private Integer suffixNoMaskLen;

	private String maskStr;

	private SensitiveType type;


	@Override
	public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
		System.out.println("type: " + type);
		switch (type) {
			case ChineseName:
				jsonGenerator.writeString(DesensitizedUtils.chineseName(value));
				break;
			case IdCard:
				jsonGenerator.writeString(DesensitizedUtils.idCardNum(value));
				break;
			case Phone:
				jsonGenerator.writeString(DesensitizedUtils.fixedPhone(value));
				break;
			case Mobile:
				jsonGenerator.writeString(DesensitizedUtils.mobilePhone(value));
				break;
			case Address:
				jsonGenerator.writeString(DesensitizedUtils.address(value));
				break;
			case Email:
				jsonGenerator.writeString(DesensitizedUtils.email(value));
				break;
			case BankCard:
				jsonGenerator.writeString(DesensitizedUtils.bankCard(value));
				break;
			case Password:
				jsonGenerator.writeString(DesensitizedUtils.password(value));
				break;
			// 暂时按key处理
			case CarNumber:
				jsonGenerator.writeString(DesensitizedUtils.key(value));
				break;
			// 自定义脱敏
			case Customize:
				jsonGenerator.writeString(DesensitizedUtils.desValue(value, prefixNoMaskLen, suffixNoMaskLen, maskStr));
				break;
			default:
				throw new IllegalArgumentException("Unknow sensitive type enum " + type);
		}
	}


	@Override
	public JsonSerializer<?> createContextual(SerializerProvider serializers, BeanProperty beanProperty) throws JsonMappingException {
		if (beanProperty != null) {
			if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
				FieldSensitive sensitive = beanProperty.getAnnotation(FieldSensitive.class);
				if (sensitive == null) {
					sensitive = beanProperty.getContextAnnotation(FieldSensitive.class);
				}
				if (sensitive != null) {
					return new SensitiveSerializer(sensitive.prefixNoMaskLen(), sensitive.suffixNoMaskLen(),
							sensitive.maskStr(), sensitive.sensitiveType());
				}
			}
			return serializers.findValueSerializer(beanProperty.getType(), beanProperty);
		}
		return serializers.findNullValueSerializer(null);
	}
}
