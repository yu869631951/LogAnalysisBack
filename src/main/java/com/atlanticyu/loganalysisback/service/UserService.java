package com.atlanticyu.loganalysisback.service;

import com.atlanticyu.loganalysisback.model.entity.User;
import com.atlanticyu.loganalysisback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //jpa关键词，https://www.jianshu.com/p/99a2962c634a，后续可直接调用，实现效果如其名
        User user = userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

}
