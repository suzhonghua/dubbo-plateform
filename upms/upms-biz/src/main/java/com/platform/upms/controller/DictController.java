package com.platform.upms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.common.core.constant.CacheConstants;
import com.platform.common.core.util.Result;
import com.platform.common.log.annotation.SysLog;
import com.platform.upms.api.entity.SysDict;
import com.platform.upms.api.entity.SysDictItem;
import com.platform.upms.api.service.SysDictItemService;
import com.platform.upms.api.service.SysDictService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author szhua
 * @since 2019-03-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dict")
@Api(value = "dict", tags = "字典管理模块")
public class DictController {

    private final SysDictItemService sysDictItemService;

    private final SysDictService sysDictService;

    /**
     * 通过ID查询字典信息
     *
     * @param id ID
     * @return 字典信息
     */
    @GetMapping("/get/{id}")
    public Result<SysDict> getById(@PathVariable Integer id) {
        return Result.ok(sysDictService.getById(id));
    }

    /**
     * 分页查询字典信息
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public Result<IPage<SysDict>> getDictPage(Page<SysDict> page, SysDict sysDict) {
        return Result.ok(sysDictService.page(page, Wrappers.query(sysDict)));
    }

    /**
     * 通过字典类型查找字典
     *
     * @param type 类型
     * @return 同类型字典
     */
    @GetMapping("/type/{type}")
    @Cacheable(value = CacheConstants.DICT_DETAILS, key = "#type")
    public Result<List<SysDictItem>> getDictByType(@PathVariable String type) {
        return Result.ok(sysDictItemService.list(Wrappers.<SysDictItem>query().lambda().eq(SysDictItem::getType, type)));
    }

    /**
     * 添加字典
     *
     * @param sysDict 字典信息
     * @return success、false
     */
    @SysLog("添加字典")
    @PostMapping("/add")
    @PreAuthorize("@pms.hasPermission('sys_dict_add')")
    public Result<Boolean> save(@Valid @RequestBody SysDict sysDict) {
        return Result.status(sysDictService.save(sysDict));
    }

    /**
     * 删除字典，并且清除字典缓存
     *
     * @param id ID
     * @return R
     */
    @SysLog("删除字典")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@pms.hasPermission('sys_dict_del')")
    public Result<Boolean> removeById(@PathVariable Integer id) {
        return Result.status(sysDictService.removeDict(id));
    }

    /**
     * 修改字典
     *
     * @param sysDict 字典信息
     * @return success/false
     */
    @PutMapping("/update")
    @SysLog("修改字典")
    @PreAuthorize("@pms.hasPermission('sys_dict_edit')")
    public Result<Boolean> updateById(@Valid @RequestBody SysDict sysDict) {
        return Result.status(sysDictService.updateDict(sysDict));
    }

    /**
     * item列表
     *
     * @param dictId 字典id
     * @return
     */
    @GetMapping("/item/list/{dictId}")
    public Result<List<SysDictItem>> getSysDictItemPage(@PathVariable Integer dictId) {
        return Result.ok(sysDictItemService.list(Wrappers.<SysDictItem>query().lambda().eq(SysDictItem::getDictId, dictId)));
    }


    /**
     * 通过id查询字典项
     *
     * @param id id
     * @return R
     */
    @GetMapping("/item/get/{id}")
    public Result<SysDictItem> getDictItemById(@PathVariable("id") Integer id) {
        return Result.ok(sysDictItemService.getById(id));
    }

    /**
     * 新增字典项
     *
     * @param sysDictItem 字典项
     * @return R
     */
    @SysLog("新增字典项")
    @PostMapping("/item/add")
    @CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
    public Result<Boolean> save(@RequestBody SysDictItem sysDictItem) {
        return Result.status(sysDictItemService.save(sysDictItem));
    }

    /**
     * 修改字典项
     *
     * @param sysDictItem 字典项
     * @return R
     */
    @SysLog("修改字典项")
    @PutMapping("/item/update")
    public Result<Boolean> updateById(@RequestBody SysDictItem sysDictItem) {
        return Result.status(sysDictItemService.updateDictItem(sysDictItem));
    }

    /**
     * 通过id删除字典项
     *
     * @param id id
     * @return R
     */
    @SysLog("删除字典项")
    @DeleteMapping("/item/delete/{id}")
    public Result<Boolean> removeDictItemById(@PathVariable Integer id) {
        return Result.status(sysDictItemService.removeDictItem(id));
    }
}
