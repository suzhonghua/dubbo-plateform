package com.platform.upms.provider;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.api.upms.dto.SysLogDTO;
import com.platform.api.upms.service.LogService;
import com.platform.common.core.util.Result;
import com.platform.upms.api.entity.SysLog;
import com.platform.upms.mapper.SysLogMapper;

/**
 * @author szhua
 */
public class LogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements LogService {
    /**
     * 保存日志
     *
     * @param sysLogDTO 日志实体 {@link SysLog}
     * @param token  jwt token
     * @return {@link Boolean}
     */
    @Override
    public Result<Boolean> saveLog(SysLogDTO sysLogDTO, String token) {
        SysLog sysLog = new SysLog();
        BeanUtil.copyProperties(sysLogDTO, sysLog);
        return Result.status(save(sysLog));
    }
}
