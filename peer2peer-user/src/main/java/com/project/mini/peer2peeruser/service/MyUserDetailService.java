package com.project.mini.peer2peeruser.service;

import com.project.mini.peer2peeruser.model.MyUserDetail;
import com.project.mini.peer2peeruser.model.User;
import com.project.mini.peer2peeruser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = usersRepository.findByName(username);
        if (user == null){
            throw new UsernameNotFoundException("No user found!");
        }
        return new MyUserDetail(user);
    }
}

