package com.platform.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.security.util.SecurityUtils;
import com.platform.upms.api.dto.DeptTree;
import com.platform.upms.api.entity.SysDept;
import com.platform.upms.api.entity.SysDeptRelation;
import com.platform.upms.api.service.SysDeptRelationService;
import com.platform.upms.api.service.SysDeptService;
import com.platform.upms.api.vo.TreeUtil;
import com.platform.upms.mapper.SysDeptMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author szhua
 * @since 2019/2/1
 */
@Service
@AllArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    private final SysDeptRelationService sysDeptRelationService;

    /**
     * 添加信息部门
     *
     * @param dept 部门
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveDept(SysDept dept) {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(dept, sysDept);
        this.save(sysDept);
        sysDeptRelationService.saveDeptRelation(sysDept);
        return Boolean.TRUE;
    }


    /**
     * 删除部门
     *
     * @param id 部门 ID
     * @return 成功、失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeDeptById(Integer id) {
        //级联删除部门
        List<Integer> idList = sysDeptRelationService
                .list(Wrappers.<SysDeptRelation>query().lambda()
                        .eq(SysDeptRelation::getAncestor, id))
                .stream()
                .map(SysDeptRelation::getDescendant)
                .collect(Collectors.toList());

        if (CollUtil.isNotEmpty(idList)) {
            this.removeByIds(idList);
        }

        //删除部门级联关系
        sysDeptRelationService.removeDeptRelationById(id);
        return Boolean.TRUE;
    }

    /**
     * 更新部门
     *
     * @param sysDept 部门信息
     * @return 成功、失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateDeptById(SysDept sysDept) {
        //更新部门状态
        this.updateById(sysDept);
        //更新部门关系
        SysDeptRelation relation = new SysDeptRelation();
        relation.setAncestor(sysDept.getParentId());
        relation.setDescendant(sysDept.getDeptId());
        sysDeptRelationService.updateDeptRelation(relation);
        return Boolean.TRUE;
    }

    /**
     * 查询全部部门树
     *
     * @return 树
     */
    @Override
    public List<DeptTree> listDeptTrees() {
        return getDeptTree(this.list(Wrappers.emptyWrapper()));
    }

    /**
     * 查询用户部门树
     *
     * @return
     */
    @Override
    public List<DeptTree> listCurrentUserDeptTrees() {
        Integer deptId = Objects.requireNonNull(SecurityUtils.getUser()).getDeptId();
        List<Integer> descendantIdList = sysDeptRelationService
                .list(Wrappers.<SysDeptRelation>query().lambda()
                        .eq(SysDeptRelation::getAncestor, deptId))
                .stream().map(SysDeptRelation::getDescendant)
                .collect(Collectors.toList());

        List<SysDept> deptList = baseMapper.selectBatchIds(descendantIdList);
        return getDeptTree(deptList);
    }

    /**
     * 构建部门树
     *
     * @param depts 部门
     * @return
     */
    private List<DeptTree> getDeptTree(List<SysDept> depts) {
        List<DeptTree> treeList = depts.stream()
                .filter(dept -> !dept.getDeptId().equals(dept.getParentId()))
                .sorted(Comparator.comparingInt(SysDept::getSort))
                .map(dept -> {
                    DeptTree node = new DeptTree();
                    node.setId(dept.getDeptId());
                    node.setParentId(dept.getParentId());
                    node.setName(dept.getName());
                    return node;
                }).collect(Collectors.toList());
        return TreeUtil.build(treeList, 0);
    }
}
