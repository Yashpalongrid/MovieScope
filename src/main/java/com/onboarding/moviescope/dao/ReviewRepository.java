package com.onboarding.moviescope.dao;

import com.onboarding.moviescope.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

      @Query("SELECT R FROM Review R WHERE R.isDeleted=0 AND R.movie.id=movie_id")
      List<Review> findByMovieId(@Param("movie_id") long movie_id);

      @Query("SELECT R FROM Review R WHERE R.isDeleted=0 AND R.user.id=?1")
      List<Review> findByUserId(long userId);

//    void deleteById(Long id);


}
