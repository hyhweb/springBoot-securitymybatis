package com.springboot.securitymybatis.controller;

import com.springboot.securitymybatis.entity.TSysRole;
import com.springboot.securitymybatis.service.TSysRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TSysRole)表控制层
 *
 * @author makejava
 * @since 2019-11-05 11:37:31
 */
@RestController
@RequestMapping("tSysRole")
public class TSysRoleController {
    /**
     * 服务对象
     */
    @Resource
    private TSysRoleService tSysRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TSysRole selectOne(Long id) {
        return this.tSysRoleService.queryById(id);
    }

}