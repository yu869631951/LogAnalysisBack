package com.atlanticyu.loganalysisback.controller;

import com.atlanticyu.loganalysisback.commo.Const;
import com.atlanticyu.loganalysisback.dto.AdminLoginParam;
import com.atlanticyu.loganalysisback.model.entity.User;
import com.atlanticyu.loganalysisback.service.AuthService;
import com.atlanticyu.loganalysisback.commo.CommonResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "JwtAuthController",description = "JWT认证管理")
@RestController
public class JwtAuthController {

    @Autowired
    private AuthService authService;

    // 登录
    @ApiOperation("登录")
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody AdminLoginParam adminLoginParam) throws AuthenticationException {
        String token = authService.login( adminLoginParam.getUsername(), adminLoginParam.getPassword() );
        if(token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        //不能单返回token，同时要带有状态码和信息、数据
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead", Const.TOKEN_PREFIX);
        return CommonResult.success(tokenMap);
    }

    //这种方法无法获取到登入的账号密码信息
/*    public String createToken(String username,String password ) throws AuthenticationException {
        System.out.println("登录mapping");
        System.out.println(username);
        System.out.println(password);
        return authService.login( username, password ); // 登录成功会返回JWT Token给用户
    }*/

    // 注册
    @ApiOperation("注册")
    @RequestMapping(value = "/authentication/register/{username}/{password}", method = RequestMethod.POST)
    public User register(@PathVariable("username") String username,@PathVariable("password") String password ) throws AuthenticationException {
        System.out.println("注册mapping");
        User addedUser = new User();
        addedUser.setUsername(username);
        addedUser.setPassword(password);
        return authService.register(addedUser);
    }
    //    public User register(@RequestBody User addedUser ) throws AuthenticationException {
//        return authService.register(addedUser);
//    }

}
