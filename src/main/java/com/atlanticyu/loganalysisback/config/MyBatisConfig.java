package com.atlanticyu.loganalysisback.config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis配置类
 * Created by macro on 2019/4/8.
 */
@Configuration
@EnableTransactionManagement
//@MapperScan({"com.atlanticyu.loganalysisback.mapper","com.atlanticyu.loganalysisback.dao"})
public class MyBatisConfig {
}

