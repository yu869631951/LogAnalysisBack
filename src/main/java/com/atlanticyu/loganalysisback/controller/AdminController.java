package com.atlanticyu.loganalysisback.controller;

import com.atlanticyu.loganalysisback.commo.Const;
import com.atlanticyu.loganalysisback.dto.AdminLoginParam;
import com.atlanticyu.loganalysisback.model.entity.Admin;
import com.atlanticyu.loganalysisback.model.entity.User;
import com.atlanticyu.loganalysisback.service.AdminService;
import com.atlanticyu.loganalysisback.commo.CommonResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "JwtAuthController",description = "JWT认证管理")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 登录
    @ApiOperation("登录")
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody AdminLoginParam adminLoginParam) throws AuthenticationException {
        String token = adminService.login( adminLoginParam.getUsername(), adminLoginParam.getPassword() );
        if(token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        //不能单返回token，同时要带有状态码和信息、数据
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead", Const.TOKEN_PREFIX);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取当前登录用户信息")
    @RequestMapping(value = "admin/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal) {
        if(principal==null){
            System.out.println("未授权的用户");
            return CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        System.out.println(username + "-----------");
        User ToSearchUser = adminService.getAdminByUsername(username);
        System.out.println("AdminController");
        System.out.println(ToSearchUser.getUsername());
        System.out.println(ToSearchUser.getPassword());
//        UmsAdmin umsAdmin = adminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", ToSearchUser.getUsername());
        data.put("roles", new String[]{"TEST"});
        //模拟菜单，title会被前端路由命名覆盖
        data.put("menus", new String[]{"[UmsMenu [Hash = 380815906, id=1, parentId=0, createTime=Sun Feb 02 14:50:36 CST 2020, title=本地日志操作, level=0, sort=0, name=localLogOperation, icon=local, hidden=0, serialVersionUID=1], UmsMenu [Hash = 680091108, id=2, parentId=1, createTime=Sun Feb 02 14:51:50 CST 2020, title=本地一, level=1, sort=0, name=one, icon=one, hidden=0, serialVersionUID=1], UmsMenu [Hash = 1709117565, id=3, parentId=1, createTime=Sun Feb 02 14:52:44 CST 2020, title=本地二, level=1, sort=0, name=two, icon=two, hidden=0, serialVersionUID=1,UmsMenu [Hash = 380815906, id=4, parentId=0, createTime=Sun Feb 02 14:50:36 CST 2020, title=本地日志操作, level=0, sort=0, name=localLogOperation, icon=local, hidden=0, serialVersionUID=1],UmsMenu [Hash = 380815906, id=5, parentId=0, createTime=Sun Feb 02 14:50:36 CST 2020, title=本地日志操作, level=0, sort=0, name=localLogOperation, icon=local, hidden=0, serialVersionUID=1]]"});
        //data.put("menus", new String[]{"[UmsMenu [Hash = 380815906, id=1, parentId=0, createTime=Sun Feb 02 14:50:36 CST 2020, title=本地日志操作, level=0, sort=0, name=localLogOperation, icon=local, hidden=0, serialVersionUID=1], UmsMenu [Hash = 680091108, id=2, parentId=1, createTime=Sun Feb 02 14:51:50 CST 2020, title=本地一, level=1, sort=0, name=one, icon=one, hidden=0, serialVersionUID=1], UmsMenu [Hash = 1709117565, id=3, parentId=1, createTime=Sun Feb 02 14:52:44 CST 2020, title=本地二, level=1, sort=0, name=two, icon=two, hidden=0, serialVersionUID=1,UmsMenu [Hash = 380815906, id=4, parentId=0, createTime=Sun Feb 02 14:50:36 CST 2020, title=本地日志操作, level=0, sort=0, name=localLogOperation, icon=local, hidden=0, serialVersionUID=1],UmsMenu [Hash = 380815906, id=5, parentId=0, createTime=Sun Feb 02 14:50:36 CST 2020, title=本地日志操作, level=0, sort=0, name=localLogOperation, icon=local, hidden=0, serialVersionUID=1]]"});
//        data.put("icon", umsAdmin.getIcon());
        return CommonResult.success(data);
    }


    //这种方法无法获取到登入的账号密码信息
/*    public String createToken(String username,String password ) throws AuthenticationException {
        System.out.println("登录mapping");
        System.out.println(username);
        System.out.println(password);
        return adminService.login( username, password ); // 登录成功会返回JWT Token给用户
    }*/

    // 注册
    @ApiOperation("注册")
    @RequestMapping(value = "/authentication/register/{username}/{password}", method = RequestMethod.POST)
    public User register(@PathVariable("username") String username,@PathVariable("password") String password ) throws AuthenticationException {
        System.out.println("注册mapping");
        User addedUser = new User();
        addedUser.setUsername(username);
        addedUser.setPassword(password);
        return adminService.register(addedUser);
    }
    //    public User register(@RequestBody User addedUser ) throws AuthenticationException {
//        return adminService.register(addedUser);
//    }
    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/admin/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success(null);
    }

}
