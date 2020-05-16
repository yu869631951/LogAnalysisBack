package com.atlanticyu.loganalysisback.dao;

import com.atlanticyu.loganalysisback.model.entity.IpAnalysisEntity;
import com.atlanticyu.loganalysisback.model.entity.NetTimeAnalysisEntity;
import com.atlanticyu.loganalysisback.model.entity.UrlAnalysisEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "LogAnaResultDao")
public interface LogAnaResultDao {
    @Select("select * from url_visit_count limit 10")
    List<UrlAnalysisEntity> getUrlAnaResultList();

    @Select("select * from ip_visit_count limit 20")
    List<IpAnalysisEntity> getIpVisitCount();

    //todo:注意之后的数据有乱码，尚未去处理，在这里相当于过滤数据
    @Select("select * from net_time limit 76")
    List<NetTimeAnalysisEntity> getNetTime();
}
