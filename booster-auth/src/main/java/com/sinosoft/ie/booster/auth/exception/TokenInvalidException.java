package com.sinosoft.ie.booster.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.ie.booster.auth.component.BoosterAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * 令牌不合法异常
 *
 * @author lengleng
 * @since 2021-08-05
 */
@JsonSerialize(using = BoosterAuth2ExceptionSerializer.class)
public class TokenInvalidException extends BoosterAuth2Exception {

	public TokenInvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_token";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.FAILED_DEPENDENCY.value();
	}

}
