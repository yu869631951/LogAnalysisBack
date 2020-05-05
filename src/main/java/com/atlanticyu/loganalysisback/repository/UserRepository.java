package com.atlanticyu.loganalysisback.repository;

import com.atlanticyu.loganalysisback.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//操作数据表，有crud和排序的功能 ，必须接口继承jpa。..，完成对db的操作
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
