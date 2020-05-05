package com.atlanticyu.loganalysisback.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testAuthController {

    // 测试普通权限，注意角色大小写
    @PreAuthorize("hasAuthority('role_normal')")
    @RequestMapping( value="/normal/test", method = RequestMethod.GET )
    public String test1() {
        System.out.println("test1");
        return "ROLE_NORMAL /normal/test接口调用成功！";
    }

    // 测试管理员权限,注意角色大小写
    @PreAuthorize("hasAuthority('role_admin')")
    @RequestMapping( value = "/admin/test", method = RequestMethod.GET )
    public String test2() {
        return "ROLE_ADMIN /admin/test接口调用成功！";
    }

}
