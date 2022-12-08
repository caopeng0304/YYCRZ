package com.sinosoft.ie.booster.common.core.validation.validator;

import com.sinosoft.ie.booster.common.core.validation.MustEqualToField;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;
import java.util.Objects;

/**
 * 校验目标字段值必须与源字段值相同
 *
 * @author haocy
 * @since 2021-12-01
 */
public class MustEqualToFieldValidator extends AbstractValidator implements ConstraintValidator<MustEqualToField, Object> {

	private String sourceField;

	private String sourceFieldLabel;

	private String targetField;

	private String targetFieldLabel;

	@Override
	public void initialize(MustEqualToField constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
		sourceField = constraintAnnotation.sourceField();
		sourceFieldLabel = constraintAnnotation.sourceFieldLabel();
		targetField = constraintAnnotation.targetField();
		targetFieldLabel = constraintAnnotation.targetFieldLabel();
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		//禁用默认的message的值
		context.disableDefaultConstraintViolation();
		//重新添加错误提示语句
		context.buildConstraintViolationWithTemplate(String.format("%s必须和%s相同", targetFieldLabel, sourceFieldLabel))
				.addPropertyNode(targetField)
				.addConstraintViolation();
		//获取字段值
		Map<String, String> fieldValueMap = getFieldValueMap(obj, sourceField, targetField);
		String sourceFieldValue = fieldValueMap.get(sourceField);
		String targetFieldValue = fieldValueMap.get(targetField);
		if (sourceFieldValue == null || targetFieldValue == null) {
			return false;
		}
		return Objects.equals(sourceFieldValue, targetFieldValue);
	}
}
