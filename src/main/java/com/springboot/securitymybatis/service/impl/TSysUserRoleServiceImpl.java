package com.springboot.securitymybatis.service.impl;

import com.springboot.securitymybatis.entity.TSysUserRole;
import com.springboot.securitymybatis.dao.TSysUserRoleDao;
import com.springboot.securitymybatis.service.TSysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TSysUserRole)表服务实现类
 *
 * @author makejava
 * @since 2019-11-05 11:37:31
 */
@Service("tSysUserRoleService")
public class TSysUserRoleServiceImpl implements TSysUserRoleService {
    @Resource
    private TSysUserRoleDao tSysUserRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userRoleId 主键
     * @return 实例对象
     */
    @Override
    public TSysUserRole queryById(Long userRoleId) {
        return this.tSysUserRoleDao.queryById(userRoleId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TSysUserRole> queryAllByLimit(int offset, int limit) {
        return this.tSysUserRoleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tSysUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public TSysUserRole insert(TSysUserRole tSysUserRole) {
        this.tSysUserRoleDao.insert(tSysUserRole);
        return tSysUserRole;
    }

    /**
     * 修改数据
     *
     * @param tSysUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public TSysUserRole update(TSysUserRole tSysUserRole) {
        this.tSysUserRoleDao.update(tSysUserRole);
        return this.queryById(tSysUserRole.getUserRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userRoleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userRoleId) {
        return this.tSysUserRoleDao.deleteById(userRoleId) > 0;
    }
}