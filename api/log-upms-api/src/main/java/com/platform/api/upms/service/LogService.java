package com.platform.api.upms.service;

import com.platform.api.upms.dto.SysLogDTO;
import com.platform.common.core.util.Result;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author szhua
 * @since 2019/2/1
 */
public interface LogService {

    /**
     * 保存日志
     *
     * @param sysLog 日志实体 {@link SysLogDTO}
     * @param from   请求头参数
     * @return {@link Boolean}
     */
    Result<Boolean> saveLog(SysLogDTO sysLog, String from);

}
