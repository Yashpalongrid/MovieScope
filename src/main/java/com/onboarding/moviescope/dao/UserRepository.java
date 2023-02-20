package com.onboarding.moviescope.dao;

import com.onboarding.moviescope.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    void deleteById(Long val);
    User findByUsername(String Username);
}
