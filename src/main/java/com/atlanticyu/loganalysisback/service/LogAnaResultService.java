package com.atlanticyu.loganalysisback.service;

import com.atlanticyu.loganalysisback.model.entity.UrlAnalysisEntity;

import java.util.List;

public interface LogAnaResultService {
    List<UrlAnalysisEntity> getUrlVisitCount();
    String getIpVisitCount();
    String getNetTime();
}
