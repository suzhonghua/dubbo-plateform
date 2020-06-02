package com.platform.common.log.aspect;

import com.platform.api.upms.dto.SysLogDTO;
import com.platform.common.core.util.SpringContextHolder;
import com.platform.common.log.annotation.SysLog;
import com.platform.common.log.event.SysLogEvent;
import com.platform.common.log.util.SysLogUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 操作日志使用spring event异步入库
 *
 * @author szhua
 */
@Aspect
@Slf4j
public class SysLogAspect {

	@Around("@annotation(sysLog)")
	@SneakyThrows
	public Object around(ProceedingJoinPoint point, SysLog sysLog) {
		String strClassName = point.getTarget().getClass().getName();
		String strMethodName = point.getSignature().getName();
		log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

		SysLogDTO sysLogDTO = SysLogUtils.getSysLog();
		sysLogDTO.setTitle(sysLog.value());
		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Object obj = point.proceed();
		Long endTime = System.currentTimeMillis();
		sysLogDTO.setTime(endTime - startTime);
		SpringContextHolder.publishEvent(new SysLogEvent(sysLogDTO));
		return obj;
	}

}
