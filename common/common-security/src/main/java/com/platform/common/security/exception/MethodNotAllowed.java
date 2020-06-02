package com.platform.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.platform.common.security.component.Auth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author szhua
 */
@JsonSerialize(using = Auth2ExceptionSerializer.class)
public class MethodNotAllowed extends Auth2Exception {

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
