package com.platform.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.platform.common.security.component.Auth2ExceptionSerializer;

/**
 * @author szhua
 */
@JsonSerialize(using = Auth2ExceptionSerializer.class)
public class InvalidException extends Auth2Exception {

	public InvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_exception";
	}

	@Override
	public int getHttpErrorCode() {
		return 426;
	}

}
