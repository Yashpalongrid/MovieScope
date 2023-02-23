package com.onboarding.moviescope.dao;

import com.onboarding.moviescope.model.constant.Genre;
import com.onboarding.moviescope.model.constant.Language;
import com.onboarding.moviescope.model.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {


//    @Query(value = "SELECT M FROM Movie M WHERE M.isDeleted=0 AND ( M.name=?1 OR M.genres=?2 OR M.languages=?3 OR M.releaseYear=?4)")
//    Page<Movie> findByFilters(String name, String genre, String language, int year, Pageable pageable);
      Page<Movie> findByNameContainingIgnoreCaseOrGenresOrLanguageOrReleaseYearAndIsDeleted(String name, Genre genres, Language language, int releaseYear, boolean isDeleted, Pageable pageable);


}
