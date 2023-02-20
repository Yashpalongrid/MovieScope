package com.onboarding.moviescope.dao;

import com.onboarding.moviescope.model.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist,Long> {

    @Query("SELECT W FROM Watchlist W WHERE W.isDeleted=0 AND W.user.id=?1")
    List<Watchlist> findMovieByUserId(long user_id);
}
