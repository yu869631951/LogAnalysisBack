package com.atlanticyu.loganalysisback.service;

import com.atlanticyu.loganalysisback.model.entity.Admin;
import com.atlanticyu.loganalysisback.model.entity.User;

public interface AdminService {
    User register( User userToAdd );
    String login( String username, String password );
    /**
     * 根据用户名获取后台管理员
     */
    User getAdminByUsername(String username);
}
