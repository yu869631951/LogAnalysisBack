package com.atlanticyu.loganalysisback.service;

import com.atlanticyu.loganalysisback.model.entity.IpAnalysisEntity;
import com.atlanticyu.loganalysisback.model.entity.NetTimeAnalysisEntity;
import com.atlanticyu.loganalysisback.model.entity.UrlAnalysisEntity;

import java.util.List;

public interface LogAnaResultService {
    List<UrlAnalysisEntity> getUrlVisitCount();
    List<IpAnalysisEntity> getIpVisitCount();
    List<NetTimeAnalysisEntity> getNetTime();
}
