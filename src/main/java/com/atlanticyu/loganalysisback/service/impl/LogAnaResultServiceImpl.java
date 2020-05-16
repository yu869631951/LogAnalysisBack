package com.atlanticyu.loganalysisback.service.impl;

import com.atlanticyu.loganalysisback.dao.LogAnaResultDao;
import com.atlanticyu.loganalysisback.model.entity.IpAnalysisEntity;
import com.atlanticyu.loganalysisback.model.entity.NetTimeAnalysisEntity;
import com.atlanticyu.loganalysisback.model.entity.UrlAnalysisEntity;
import com.atlanticyu.loganalysisback.service.LogAnaResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogAnaResultServiceImpl implements LogAnaResultService {

    @Autowired
    private LogAnaResultDao logAnaResultDao;

    @Override
    public List<UrlAnalysisEntity> getUrlVisitCount() {
        return logAnaResultDao.getUrlAnaResultList();
    }

    @Override
    public List<IpAnalysisEntity> getIpVisitCount() {
        return logAnaResultDao.getIpVisitCount();
    }

    @Override
    public List<NetTimeAnalysisEntity> getNetTime() {
        return logAnaResultDao.getNetTime();
    }
}
