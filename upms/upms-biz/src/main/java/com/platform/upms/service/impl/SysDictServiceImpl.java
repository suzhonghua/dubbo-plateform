package com.platform.upms.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.core.constant.CacheConstants;
import com.platform.common.core.constant.enums.DictTypeEnum;
import com.platform.upms.api.entity.SysDict;
import com.platform.upms.api.entity.SysDictItem;
import com.platform.upms.api.service.SysDictService;
import com.platform.upms.mapper.SysDictItemMapper;
import com.platform.upms.mapper.SysDictMapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 字典表
 *
 * @author szhua
 * @date 2019/03/19
 */
@Service
@AllArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    private final SysDictItemMapper dictItemMapper;

    /**
     * 根据ID 删除字典
     *
     * @param id 字典ID
     * @return
     */
    @Override
    @CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public boolean removeDict(Integer id) {
        SysDict dict = this.getById(id);
        // 系统内置
        Assert.isFalse(DictTypeEnum.SYSTEM.getType().equals(dict.getSystem()), "系统内置字典不能删除");
        baseMapper.deleteById(id);
        dictItemMapper.delete(Wrappers.<SysDictItem>lambdaQuery()
                .eq(SysDictItem::getDictId, id));
        return Boolean.TRUE;
    }

    /**
     * 更新字典
     *
     * @param dict 字典
     * @return
     */
    @Override
    public boolean updateDict(SysDict dict) {
        SysDict sysDict = this.getById(dict.getId());
        // 系统内置
        Assert.isFalse(DictTypeEnum.SYSTEM.getType().equals(sysDict.getSystem()), "系统内置字典不能修改");
        return this.updateById(dict);
    }
}
