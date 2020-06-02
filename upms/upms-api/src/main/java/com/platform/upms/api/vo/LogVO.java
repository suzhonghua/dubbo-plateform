package com.platform.upms.api.vo;

import com.platform.upms.api.entity.SysLog;
import lombok.Data;

import java.io.Serializable;

/**
 * @author szhua
 */
@Data
public class LogVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private SysLog sysLog;
	private String username;
}
