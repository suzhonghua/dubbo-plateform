package com.platform.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.upms.api.entity.SysDeptRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author szhua
 * @since 2019/2/1
 */
@Mapper
public interface SysDeptRelationMapper extends BaseMapper<SysDeptRelation> {
	/**
	 * 删除部门关系表数据
	 *
	 * @param id 部门ID
	 */
	void deleteDeptRelationsById(Integer id);

	/**
	 * 更改部分关系表数据
	 *
	 * @param deptRelation
	 */
	void updateDeptRelations(SysDeptRelation deptRelation);

}
