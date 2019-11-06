package com.springboot.securitymybatis.controller;

import com.springboot.securitymybatis.entity.TSysUser;
import com.springboot.securitymybatis.service.TSysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TSysUser)表控制层
 *
 * @author makejava
 * @since 2019-11-05 11:37:31
 */
@RestController
@RequestMapping("tSysUser")
public class TSysUserController {
    /**
     * 服务对象
     */
    @Resource
    private TSysUserService tSysUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TSysUser selectOne(Long id) {
        return this.tSysUserService.queryById(id);
    }

}