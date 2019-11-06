package com.springboot.securitymybatis.service;

import com.springboot.securitymybatis.entity.TSysRole;
import java.util.List;

/**
 * (TSysRole)表服务接口
 *
 * @author makejava
 * @since 2019-11-05 11:37:31
 */
public interface TSysRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    TSysRole queryById(Long roleId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TSysRole> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tSysRole 实例对象
     * @return 实例对象
     */
    TSysRole insert(TSysRole tSysRole);

    /**
     * 修改数据
     *
     * @param tSysRole 实例对象
     * @return 实例对象
     */
    TSysRole update(TSysRole tSysRole);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Long roleId);

}