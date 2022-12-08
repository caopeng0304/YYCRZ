package com.sinosoft.ie.booster.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.ie.booster.auth.component.BoosterAuth2ExceptionSerializer;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义OAuth2Exception
 *
 * @author lengleng
 * @since 2019/2/1
 */
@JsonSerialize(using = BoosterAuth2ExceptionSerializer.class)
public class BoosterAuth2Exception extends OAuth2Exception {

	@Getter
	private String errorCode;

	public BoosterAuth2Exception(String msg) {
		super(msg);
	}

	public BoosterAuth2Exception(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

}
