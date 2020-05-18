package com.example.securitylearn.service.impl;

import com.example.securitylearn.dao.UserRepository;
import com.example.securitylearn.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("call ==> loadUserByUsername");
        User user = userRepository.findByUsername(username);
        if (user != null){
            log.info(user.toString());
            return user;
        }
        throw new UsernameNotFoundException("User:" + username + "not found");
    }


}
