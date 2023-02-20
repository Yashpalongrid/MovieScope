package com.onboarding.moviescope.dao;

import com.onboarding.moviescope.model.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist,Long> {

    @Query("SELECT movie.id FROM Watchlist W WHERE W.isDeleted=0 AND W.user.id=user_id")
    List<Long> findMovieByUserId(long user_id);
}
