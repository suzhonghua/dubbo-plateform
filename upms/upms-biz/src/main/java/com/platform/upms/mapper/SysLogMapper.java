package com.platform.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.upms.api.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 日志表 Mapper 接口
 * </p>
 *
 * @author szhua
 * @since 2019/2/1
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {
}
