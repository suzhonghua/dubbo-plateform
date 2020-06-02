package com.platform.common.log.event;

import com.platform.api.upms.dto.SysLogDTO;
import org.springframework.context.ApplicationEvent;

/**
 * @author szhua
 * 系统日志事件
 */
public class SysLogEvent extends ApplicationEvent {

	private static final long serialVersionUID = -4650175478840032522L;

	public SysLogEvent(SysLogDTO source) {
		super(source);
	}
}
