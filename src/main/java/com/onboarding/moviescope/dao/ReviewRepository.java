package com.onboarding.moviescope.dao;

import com.onboarding.moviescope.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

      @Query(value = "SELECT R FROM Review R WHERE R.isDeleted=0 AND R.movie.id=?1")
      Page<Review> findByMovieId(long movie_id, Pageable pageable);

      @Query("SELECT R FROM Review R WHERE R.isDeleted=0 AND R.user.id=?1")
      List<Review> findByUserId(long userId);

//    void deleteById(Long id);


}
