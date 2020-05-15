package com.atlanticyu.loganalysisback.controller;

import com.atlanticyu.loganalysisback.commo.CommonResult;
import com.atlanticyu.loganalysisback.model.entity.UrlAnalysisEntity;
import com.atlanticyu.loganalysisback.service.LogAnaResultService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LogAnalysisResultController {
    @Autowired
    private LogAnaResultService logAnaResultService;


    @ApiOperation("获取url访问排行")
    @RequestMapping(value = "/analysisResult/urlVisitCount", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult geturlVisitCount(Principal principal) {
        if(principal==null){
            System.out.println("未授权的用户");
            return CommonResult.unauthorized(null);
        }
        List<UrlAnalysisEntity> urlAnalysisEntityList = logAnaResultService.getUrlVisitCount();
        System.out.println("我来了");
        System.out.println(urlAnalysisEntityList);
        Map<String, Object> data = new HashMap<>();
        data.put("UrlAnaResult",urlAnalysisEntityList);
        return CommonResult.success(data);
    }
}
