package com.platform.upms.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.core.constant.CacheConstants;
import com.platform.common.core.constant.enums.DictTypeEnum;
import com.platform.upms.api.entity.SysDict;
import com.platform.upms.api.entity.SysDictItem;
import com.platform.upms.api.service.SysDictItemService;
import com.platform.upms.api.service.SysDictService;
import com.platform.upms.mapper.SysDictItemMapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * 字典项
 *
 * @author szhua
 * @date 2019/03/19
 */
@Service
@AllArgsConstructor
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {

    private final SysDictService dictService;

    /**
     * 删除字典项
     *
     * @param id 字典项ID
     * @return
     */
    @Override
    @CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
    public boolean removeDictItem(Integer id) {
        //根据ID查询字典ID
        SysDictItem dictItem = this.getById(id);
        SysDict dict = dictService.getById(dictItem.getDictId());
        // 系统内置
        Assert.isFalse(DictTypeEnum.SYSTEM.getType().equals(dict.getSystem()), "系统内置字典项目不能删除");
        return this.removeById(id);
    }

    /**
     * 更新字典项
     *
     * @param item 字典项
     * @return
     */
    @Override
    @CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
    public boolean updateDictItem(SysDictItem item) {
        //查询字典
        SysDict dict = dictService.getById(item.getDictId());
        // 系统内置
        Assert.isFalse(DictTypeEnum.SYSTEM.getType().equals(dict.getSystem()), "系统内置字典项目不能删除");
        return this.updateById(item);
    }
}
