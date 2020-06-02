package com.platform.common.security.component;

import com.platform.common.core.constant.CommonConstants;
import com.platform.common.security.exception.Auth2Exception;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.SneakyThrows;

/**
 * @author szhua
 * <p>
 * OAuth2 异常格式化
 */
public class Auth2ExceptionSerializer extends StdSerializer<Auth2Exception> {
	private static final long serialVersionUID = 3482485132705299472L;

	public Auth2ExceptionSerializer() {
		super(Auth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(Auth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeObjectField("code", CommonConstants.FAIL);
		gen.writeStringField("msg", value.getMessage());
		gen.writeStringField("data", value.getErrorCode());
		gen.writeEndObject();
	}
}
