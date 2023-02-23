package com.onboarding.moviescope.service.impl;

import com.onboarding.moviescope.dao.UserRepository;
import com.onboarding.moviescope.model.entity.User;
import com.onboarding.moviescope.model.request.RegisterRequest;
import com.onboarding.moviescope.service.UserService;
import com.onboarding.moviescope.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException(AppConstants.USER_NOT_FOUND);
        }
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("READ_AUTHORITY"));
        authorities.add(new SimpleGrantedAuthority("WRITE_PRIVILEGE"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }


    @Override
    public User registerUser(RegisterRequest registerRequest) throws IllegalArgumentException {
        if(userRepository.findByUsername(registerRequest.getUsername())!=null){
            throw new IllegalArgumentException(AppConstants.USERNAME_ALREADY_EXISTS);
        }
        User user =new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        if(registerRequest.getPassword().equals(registerRequest.getConfirmPassword())){
           user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        }else{
            throw new IllegalArgumentException(AppConstants.PASSWORD_NOT_MATCHED);
        }
        userRepository.save(user);
        return user;
    }


    @Override
    public User getUser(String username) throws IllegalArgumentException{
        User user=userRepository.findByUsername(username);
        if(user==null){
            throw new IllegalArgumentException(AppConstants.USER_NOT_FOUND);
        }
        return user;
    }
}


//    @Override
//    public User findUser(long id){
//        User user=userRepository.findById(id).orElse(null);
//        if(user==null){
//            throw new IllegalArgumentException(AppConstants.USER_NOT_FOUND)
//        }
//        return user;
//    }
