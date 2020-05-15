package com.atlanticyu.loganalysisback.controller;

import com.atlanticyu.loganalysisback.scala.sparkstreaming.OperateFileDirectory;
import com.atlanticyu.loganalysisback.util.HDFSCommonUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.hadoop.conf.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
public class FileController {
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    @ApiOperation("上传待分析文件")
    public void getFileToHDFS(@RequestParam("file") MultipartFile file){
        try {
            if (file != null) {
                File hh = HDFSCommonUtil.MultipartFileToFile(file);
                HDFSCommonUtil.copyfileToHdfs(new Configuration(),"hdfs://192.168.248.128:9000/",hh);
            }
        }
        catch(Exception io)
        {
            io.printStackTrace();
        }
    }

    @RequestMapping(value = "file/analysis", method = RequestMethod.GET)
    @ApiOperation("开始分析文件")
    public void beginAnalysis() {
        System.out.println("file/analysis的Controller已进入");
        new OperateFileDirectory().start();
        new OperateFileDirectory().AnalysisNetTime();
        new OperateFileDirectory().AnalysisIP();
        new OperateFileDirectory().AnalysisUrl();
        System.out.println("已完成OperateFileDirectory操作");
    }
}
