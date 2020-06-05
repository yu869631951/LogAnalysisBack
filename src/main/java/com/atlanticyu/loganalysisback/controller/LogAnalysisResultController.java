package com.atlanticyu.loganalysisback.controller;

import com.atlanticyu.loganalysisback.commo.CommonResult;
import com.atlanticyu.loganalysisback.model.entity.IpAnalysisEntity;
import com.atlanticyu.loganalysisback.model.entity.NetTimeAnalysisEntity;
import com.atlanticyu.loganalysisback.model.entity.UrlAnalysisEntity;
import com.atlanticyu.loganalysisback.service.LogAnaResultService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public CommonResult getUrlVisitCount(Principal principal) {
        if(principal==null){
            System.out.println("未授权的用户");
            return CommonResult.unauthorized(null);
        }
        List<UrlAnalysisEntity> urlAnalysisEntityList = logAnaResultService.getUrlVisitCount();
        System.out.println(urlAnalysisEntityList);
        Map<String, Object> data = new HashMap<>();
        data.put("UrlAnaResult",urlAnalysisEntityList);
        return CommonResult.success(data);
    }

    @ApiOperation("获取Ip访问排行")
    @RequestMapping(value = "/analysisResult/IpVisitCount", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getIpVisitCount(Principal principal) {
        if(principal==null){
            System.out.println("未授权的用户");
            return CommonResult.unauthorized(null);
        }
        List<IpAnalysisEntity> ipAnalysisEntities = logAnaResultService.getIpVisitCount();
        System.out.println(ipAnalysisEntities);
        Map<String, Object> data = new HashMap<>();
        data.put("IpAnaResult",ipAnalysisEntities);
        return CommonResult.success(data);
    }

    @ApiOperation("根据时间分析网络请求次数")
    @RequestMapping(value = "/analysisResult/netTimeInfo", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getNetTime(Principal principal) throws ParseException {
        if(principal==null){
            System.out.println("未授权的用户");
            return CommonResult.unauthorized(null);
        }
        List<NetTimeAnalysisEntity> netTimeAnalysisEntities = logAnaResultService.getNetTime();
        System.out.println(netTimeAnalysisEntities);
        for (int i = 0;i < netTimeAnalysisEntities.size();i ++) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = netTimeAnalysisEntities.get(i).getTime_value();
            if(time != null) {
                Long timestamp  = Long.parseLong(time) * 1000;
                netTimeAnalysisEntities.get(i).setTime_value(df.format(timestamp));
            }
        }
        System.out.println(netTimeAnalysisEntities);
        Map<String, Object> data = new HashMap<>();
        data.put("NetTimeAnaResult",netTimeAnalysisEntities);
        return CommonResult.success(data);
    }
}
