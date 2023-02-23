package com.onboarding.moviescope.dao;

import com.onboarding.moviescope.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

      @Query(value = "SELECT R FROM Review R WHERE R.isDeleted=0 AND R.movie.id=?1")
      Page<Review> findByMovieId(long movie_id, Pageable pageable);

      @Query(value="SELECT R FROM Review R WHERE R.isDeleted=0 AND R.user.id=?1")
      Page<Review> findByUserId(long userId,Pageable pageable);

//    void deleteById(Long id);


}
