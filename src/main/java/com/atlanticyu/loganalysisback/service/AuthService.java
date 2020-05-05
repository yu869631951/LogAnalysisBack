package com.atlanticyu.loganalysisback.service;

import com.atlanticyu.loganalysisback.model.entity.User;

public interface AuthService {
    User register( User userToAdd );
    String login( String username, String password );
}
