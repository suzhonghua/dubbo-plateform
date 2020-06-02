package com.platform.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.platform.common.security.component.Auth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author szhua
 */
@JsonSerialize(using = Auth2ExceptionSerializer.class)
public class UnauthorizedException extends Auth2Exception {

	public UnauthorizedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "unauthorized";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.UNAUTHORIZED.value();
	}

}
