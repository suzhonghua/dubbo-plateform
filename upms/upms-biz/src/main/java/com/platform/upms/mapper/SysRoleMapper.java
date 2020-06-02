package com.platform.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.upms.api.entity.SysRole;
import com.platform.upms.api.vo.SysRoleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author szhua
 * @since 2019/2/1
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 通过用户ID，查询角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> listRolesByUserId(Integer userId);

    /**
     * 角色包含菜单列表
     *
     * @param id
     * @return
     */
    SysRoleVO get(Integer id);
}
