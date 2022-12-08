package com.sinosoft.ie.booster.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.ie.booster.auth.component.BoosterAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author lengleng
 * @since 2019/2/1
 */
@JsonSerialize(using = BoosterAuth2ExceptionSerializer.class)
public class ServerErrorException extends BoosterAuth2Exception {

	public ServerErrorException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "server_error";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

}
