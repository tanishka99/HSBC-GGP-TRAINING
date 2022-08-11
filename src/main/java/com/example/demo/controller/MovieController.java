package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Movie;
import com.example.demo.exceptions.ErrorDetails;
import com.example.demo.payload.MovieDTO;
import com.example.demo.services.MovieService;

@RestController
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/api/movies/{id}")
	public ResponseEntity<MovieDTO> getMovie(@PathVariable("id") int movieId) {		
		MovieDTO movie = movieService.getMovie(movieId);
		return new ResponseEntity<MovieDTO>(movie, HttpStatus.OK);
//		if(movie!= null) {
//			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<Movie>(movie, HttpStatus.NOT_FOUND);
//		}
	}
	
	@GetMapping("/api/movies")
	public ResponseEntity<List<MovieDTO>> getAllMovies(){
		List<MovieDTO> movieList = movieService.getAllMovies();
		return new ResponseEntity<List<MovieDTO>>(movieList, HttpStatus.OK);
	}
	
	@PostMapping("/api/movies")
	public Movie saveMovie(@Valid @RequestBody Movie movie) {
		return movieService.insertMovie(movie);
	}
	
	@PutMapping("/api/movies")
	public MovieDTO updateMovie(@Valid @RequestBody Movie movie, @PathVariable("id") int movieId) {
		return movieService.updateMovie(movie, movieId);
	}
	
}
