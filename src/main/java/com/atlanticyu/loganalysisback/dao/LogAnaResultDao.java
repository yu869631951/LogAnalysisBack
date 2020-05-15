package com.atlanticyu.loganalysisback.dao;

import com.atlanticyu.loganalysisback.model.entity.UrlAnalysisEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface LogAnaResultDao {
    @Select("select * from url_visit_count limit 10")
    List<UrlAnalysisEntity> getUrlAnaResultList();
}
