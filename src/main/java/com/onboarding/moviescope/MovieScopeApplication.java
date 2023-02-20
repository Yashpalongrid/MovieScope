package com.onboarding.moviescope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MovieScopeApplication  { //implements CommandLineRunner


//	@PostConstruct
//	void init() {
//		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//	}

	public static void main(String[] args) {
		SpringApplication.run(MovieScopeApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
//
//	@Autowired
//	private UserServiceImpl userService;

//	@Autowired
//	private MovieRepository movieRepository;
//	@Autowired
//	private ReviewRepository reviewRepository;
//
//	@Autowired
//	private WatchlistRepository watchlistRepository;
//
//
//
//	@Override
//	public void run(String... args) throws Exception {
////
////
////
////
//		User user=new User();
//
//		user.setUsername("karan");
//		user.setEmail("karan@gmail.com");
//		user.setPassword("1234");
//
//		userService.registerUser(user);
////
//
////
////
//		Movie movie=new Movie();
//		movie.setName("Inception ");
//		movie.setStoryline("mind games in the movie ");
//		movie.setAverageRating(9F);
//		movie.setImgSource("httppass");
//		Set<String> cast=new HashSet<>();
//		cast.add("Leonardo DiCaprio");
//		cast.add("Elliot Page");
//		movie.setCast(cast);
//		Set<Genre> genres=new HashSet<>();
//		genres.add(Genre.Action);
//		genres.add(Genre.Drama);
//		genres.add(Genre.Thrillers);
//		movie.setGenres(genres);
//		movie.setLanguages(Language.English);
//		Set<StreamingPlatform> streamingPlatformSet=new HashSet<>();
//		streamingPlatformSet.add(StreamingPlatform.Netflix);
//		streamingPlatformSet.add(StreamingPlatform.Hotstar);
//		movie.setStreamingPlatforms(streamingPlatformSet);
//		movie.setReleaseYear(2010);
//		movieRepository.save(movie);
//
//		Review review=new Review();
//    	review.setFeedback("nice movie");
//		review.setRating(6F);
//		review.setMovie(movie);
//		review.setUser(user);
//		reviewRepository.save(review);
//
//		Watchlist watchlist=new Watchlist();
//		watchlist.setMovie(movie);
//		watchlist.setUser(user);
//		watchlistRepository.save(watchlist);


//		reviewRepository.deleteById(2);
//
//

//		Set<Review> reviewSet=new HashSet<>();
//		reviewSet.add(review);
//		user.setReviews(reviewSet);

}







