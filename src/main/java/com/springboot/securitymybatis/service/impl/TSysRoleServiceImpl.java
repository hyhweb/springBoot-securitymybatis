package com.springboot.securitymybatis.service.impl;

import com.springboot.securitymybatis.entity.TSysRole;
import com.springboot.securitymybatis.dao.TSysRoleDao;
import com.springboot.securitymybatis.service.TSysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TSysRole)表服务实现类
 *
 * @author makejava
 * @since 2019-11-05 11:37:31
 */
@Service("tSysRoleService")
public class TSysRoleServiceImpl implements TSysRoleService {
    @Resource
    private TSysRoleDao tSysRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public TSysRole queryById(Long roleId) {
        return this.tSysRoleDao.queryById(roleId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TSysRole> queryAllByLimit(int offset, int limit) {
        return this.tSysRoleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tSysRole 实例对象
     * @return 实例对象
     */
    @Override
    public TSysRole insert(TSysRole tSysRole) {
        this.tSysRoleDao.insert(tSysRole);
        return tSysRole;
    }

    /**
     * 修改数据
     *
     * @param tSysRole 实例对象
     * @return 实例对象
     */
    @Override
    public TSysRole update(TSysRole tSysRole) {
        this.tSysRoleDao.update(tSysRole);
        return this.queryById(tSysRole.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long roleId) {
        return this.tSysRoleDao.deleteById(roleId) > 0;
    }
}