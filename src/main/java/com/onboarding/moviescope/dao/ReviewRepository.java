package com.onboarding.moviescope.dao;

import com.onboarding.moviescope.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

//    @Query("SELECT R FROM review R WHERE R.deleted=0 AND R.user_id=?1")
//    List<Review> findByUserId(long user_id);

//    void deleteById(Long id);


}
