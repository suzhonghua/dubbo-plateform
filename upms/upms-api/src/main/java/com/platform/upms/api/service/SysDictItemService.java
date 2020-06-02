package com.platform.upms.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.upms.api.entity.SysDictItem;

/**
 * 字典项
 *
 * @author szhua
 * @date 2019/03/19
 */
public interface SysDictItemService extends IService<SysDictItem> {

	/**
	 * 删除字典项
	 *
	 * @param id 字典项ID
	 * @return
	 */
	boolean removeDictItem(Integer id);

	/**
	 * 更新字典项
	 *
	 * @param item 字典项
	 * @return
	 */
	boolean updateDictItem(SysDictItem item);
}
