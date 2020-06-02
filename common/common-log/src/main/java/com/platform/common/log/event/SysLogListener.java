package com.platform.common.log.event;

import com.platform.api.upms.dto.SysLogDTO;
import com.platform.api.upms.service.LogService;
import com.platform.common.core.constant.SecurityConstants;
import com.platform.common.core.util.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;


/**
 * @author szhua
 * 异步监听日志事件
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener {

    private final LogService logService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLogDTO sysLog = (SysLogDTO) event.getSource();
        Result<Boolean> result = logService.saveLog(sysLog, SecurityConstants.INNER_TOKEN);
        if (result.getCode() != 0) {
            log.warn("日志插入失败, {}", sysLog);
        }
    }
}
