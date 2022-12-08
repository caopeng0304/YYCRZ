package com.sinosoft.ie.booster.common.core.validation.validator;

import com.sinosoft.ie.booster.common.core.validation.NotEqualToField;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;
import java.util.Objects;

/**
 * 校验目标字段值不能与源字段值相同
 *
 * @author haocy
 * @since 2021-11-29
 */
public class NotEqualToFieldValidator extends AbstractValidator implements ConstraintValidator<NotEqualToField, Object> {

	private String sourceField;

	private String sourceFieldLabel;

	private String targetField;

	private String targetFieldLabel;

	@Override
	public void initialize(NotEqualToField constraintAnnotation) {
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
		context.buildConstraintViolationWithTemplate(String.format("%s不能和%s相同且不能包含%s", targetFieldLabel, sourceFieldLabel, sourceFieldLabel))
				.addPropertyNode(targetField)
				.addConstraintViolation();
		//获取字段值
		Map<String, String> fieldValueMap = getFieldValueMap(obj, sourceField, targetField);
		String sourceFieldValue = fieldValueMap.get(sourceField);
		String targetFieldValue = fieldValueMap.get(targetField);
		if (sourceFieldValue == null || targetFieldValue == null) {
			return true;
		}
		return !Objects.equals(sourceFieldValue, targetFieldValue) && !targetFieldValue.contains(sourceFieldValue);
	}
}
