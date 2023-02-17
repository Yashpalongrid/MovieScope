package com.onboarding.moviescope;

import com.onboarding.moviescope.dao.MovieRepository;
import com.onboarding.moviescope.dao.ReviewRepository;
import com.onboarding.moviescope.dao.UserRepository;
import com.onboarding.moviescope.dao.WatchlistRepository;
import com.onboarding.moviescope.model.constant.Genre;
import com.onboarding.moviescope.model.constant.Language;
import com.onboarding.moviescope.model.constant.StreamingPlatform;
import com.onboarding.moviescope.model.entity.Movie;
import com.onboarding.moviescope.model.entity.Review;
import com.onboarding.moviescope.model.entity.User;
import com.onboarding.moviescope.model.entity.Watchlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

@SpringBootApplication
public class MovieScopeApplication implements CommandLineRunner {


	@PostConstruct
	void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieScopeApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private WatchlistRepository watchlistRepository;



	@Override
	public void run(String... args) throws Exception {




		User user=new User();
		user.setUsername("gameofya6sh1");
		user.setEmail("main13@gmail.com");
		user.setPassword("shk23jdhkjsfh1");
		userRepository.save(user);


//
//
		Movie movie=new Movie();
		movie.setName("Inception ");
		movie.setStoryline("mind games in the movie ");
		movie.setAverageRating(9F);
		movie.setImgSource("httppass");
		Set<String> cast=new HashSet<>();
		cast.add("Leonardo DiCaprio");
		cast.add("Elliot Page");
		movie.setCast(cast);
		Set<Genre> genres=new HashSet<>();
		genres.add(Genre.Action);
		genres.add(Genre.Drama);
		genres.add(Genre.Thrillers);
		movie.setGenres(genres);
		movie.setLanguages(Language.English);
		Set<StreamingPlatform> streamingPlatformSet=new HashSet<>();
		streamingPlatformSet.add(StreamingPlatform.Netflix);
		streamingPlatformSet.add(StreamingPlatform.Hotstar);
		movie.setStreamingPlatforms(streamingPlatformSet);
		movie.setReleaseYear(2010);
		movieRepository.save(movie);

		Review review=new Review();
    	review.setFeedback("nice movie");
		review.setRating(6F);
		review.setMovie(movie);
		review.setUser(user);
		reviewRepository.save(review);

		Watchlist watchlist=new Watchlist();
		watchlist.setMovie(movie);
		watchlist.setUser(user);
		watchlistRepository.save(watchlist);


//		reviewRepository.deleteById(2);
//
//

//		Set<Review> reviewSet=new HashSet<>();
//		reviewSet.add(review);
//		user.setReviews(reviewSet);
	}






}
