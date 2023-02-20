package com.onboarding.moviescope.service.impl;

import com.onboarding.moviescope.dao.UserRepository;
import com.onboarding.moviescope.model.entity.User;
import com.onboarding.moviescope.model.request.RegisterRequest;
import com.onboarding.moviescope.service.UserService;
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

//    private boolean checkPassword(String password,String conformPassword ){
//        for(int i=0;i<password.length();i++){
//            if(password.charAt(i) != conformPassword.charAt(i)){
//                return false;
//            }
//        }
//        return true;
//    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("user not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("READ_AUTHORITY"));
        authorities.add(new SimpleGrantedAuthority("WRITE_PRIVILEGE"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }


    @Override
    public User registerUser(RegisterRequest registerRequest) {
        User user =new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
       // user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
       // user.setPassword(registerRequest.getPassword());
        if(registerRequest.getPassword().equals(registerRequest.getConfirmPassword())){
           user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        } ///else mai exception throw kr denge
        userRepository.save(user);
        return user;
    }

//    @Override
//    public User saveUser(User user) {
//        User u=new User();
//        u.set
//    }

    @Override
    public User findUser(long id){
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
