package com.platform.upms.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.upms.api.entity.SysDict;

/**
 * 字典表
 *
 * @author szhua
 * @date 2019/03/19
 */
public interface SysDictService extends IService<SysDict> {

	/**
	 * 根据ID 删除字典
	 *
	 * @param id
	 * @return
	 */
	boolean removeDict(Integer id);

	/**
	 * 更新字典
	 *
	 * @param sysDict 字典
	 * @return
	 */
	boolean updateDict(SysDict sysDict);
}
