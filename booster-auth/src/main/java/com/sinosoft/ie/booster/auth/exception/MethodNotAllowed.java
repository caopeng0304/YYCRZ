package com.sinosoft.ie.booster.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.ie.booster.auth.component.BoosterAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author lengleng
 * @since 2019/2/1
 */
@JsonSerialize(using = BoosterAuth2ExceptionSerializer.class)
public class MethodNotAllowed extends BoosterAuth2Exception {

	public MethodNotAllowed(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "method_not_allowed";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.METHOD_NOT_ALLOWED.value();
	}

}
