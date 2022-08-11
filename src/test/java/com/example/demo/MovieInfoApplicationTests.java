package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.entities.Movie;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.services.MovieService;

@SpringBootTest
class MovieInfoApplicationTests {

	@Autowired
	private MovieService movieService;

	@Autowired
	private MovieRepository movieRepository;

	@Test
	void contextLoads() {
	}

//	
//	@Test
//	public void testInsertMovieOperation() {
//		Movie movie = new Movie("HIT","Murder Drama",200);
//		movieService.insertMovie(movie); 
//	}
//	
//	@Test
//	public void testGetAllMovies() {
//		List<Movie> movies = movieService.getAllMovies();
//		for (Movie m : movies) {
//			System.out.println(m);
//		}
//	}

//	@Test
//	public void testPaging() {
//		Pageable pageable = PageRequest.of(0, 5);
//		Page<Movie> page = movieRepository.findAll(pageable);
//		page.forEach(movie -> System.out.println(movie));
//	}

//	@Test
//	public void testSort() {
//		Iterable<Movie> movies = movieRepository.findAll(Sort.by("description"));
//		movies.forEach(movie -> System.out.println(movie));
////	}
//	
//	@Test
//	public void testFindAllByDescription() {
//		List<Movie> movies = movieRepository.findAllByDescription("Drama");
//		movies.forEach(movie -> System.out.println(movie));
//	}
	
	@Test
	public void testFindAllByPrice() {
		List<Movie> movies = movieRepository.findAllByPriceBetween(200,500);
		movies.forEach(movie -> System.out.println(movie));
	}
}
