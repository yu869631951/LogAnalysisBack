package com.atlanticyu.loganalysisback.service.impl;

import com.atlanticyu.loganalysisback.model.entity.User;
import com.atlanticyu.loganalysisback.repository.UserRepository;
import com.atlanticyu.loganalysisback.service.AuthService;
import com.atlanticyu.loganalysisback.util.JwtTokenUtil;
import com.mysql.cj.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User userToAdd) {
       // System.out.println("注册服务1");
        final String username = userToAdd.getUsername();
        if( userRepository.findByUsername(username)!=null ) {
            return null;
        }
        //System.out.println("注册服务2");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword( encoder.encode(rawPassword) );
        return userRepository.save(userToAdd);
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername( username );
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken( username, password );
            //AuthenticationManager认证成功后将会返回一个封装了用户权限等信息的Authentication对象。
            Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            System.out.println("登录异常:" + e.getMessage());
        }
        return token;
    }
}
