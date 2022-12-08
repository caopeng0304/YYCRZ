package com.sinosoft.ie.booster.common.core.validation.validator;

import com.sinosoft.ie.booster.common.core.constant.enums.OrderTypeEnum;
import com.sinosoft.ie.booster.common.core.validation.NotContinuousOrderChar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验不能包含连续X个以及以上有顺序的字母或数字
 *
 * @author haocy
 * @since 2021-11-30
 */
public class NotContinuousOrderCharValidator implements ConstraintValidator<NotContinuousOrderChar, String> {

	private int minInteger;

	private OrderTypeEnum orderType;

	@Override
	public void initialize(NotContinuousOrderChar constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
		minInteger = constraintAnnotation.minInteger();
		orderType = constraintAnnotation.orderType();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		char[] chars = value.toCharArray();
		boolean isContinuousAsc = isContinuousOrder(chars, OrderTypeEnum.ASC);
		boolean isContinuousDesc = isContinuousOrder(chars, OrderTypeEnum.DESC);
		switch (orderType) {
			case ASC:
				if (isContinuousAsc) {
					return false;
				}
				break;
			case DESC:
				if (isContinuousDesc) {
					return false;
				}
				break;
			default:
				if (isContinuousAsc || isContinuousDesc) {
					return false;
				}
				break;
		}
		return true;
	}

	/**
	 * 判断连续字符的基本算法为：
	 * 正序 n1 + 1 == n2 && n2 + 1 == n3 && ...
	 * 倒序 n1 - 1 == n2 && n2 - 1 == n3 && ...
	 *
	 * @param chars     字符数组
	 * @param orderType 排序类型
	 * @return 是否连续顺序
	 */
	private boolean isContinuousOrder(char[] chars, OrderTypeEnum orderType) {
		boolean isContinuousOrder = true;
		for (int i = 0; i < chars.length - minInteger - 1; i++) {
			int preItem = chars[i];
			for (int j = i + 1; j < minInteger; j++) {
				int curItem = chars[j];
				int result = orderType == OrderTypeEnum.ASC ? preItem + 1 : preItem - 1;
				if (result != curItem) {
					isContinuousOrder = false;
					break;
				}
				preItem = curItem;
			}
		}
		return isContinuousOrder;
	}
}
