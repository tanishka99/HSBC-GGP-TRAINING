package com.example.demo.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Movie;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payload.MovieDTO;
import com.example.demo.repositories.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Movie insertMovie(Movie movie) {
		return movieRepository.save(movie);
	}
	
	public MovieDTO getMovie(int movieId) {
		Movie movie = movieRepository.findById(movieId).
				orElseThrow(() -> new ResourceNotFoundException("Movie", "movieId", movieId+ ""));
//		MovieDTO movieDTO = new MovieDTO();
//		movieDTO.setMovieId(movie.getMovieId()); 
//		movieDTO.setTitle(movie.getTitle());
//		movieDTO.setDescription(movie.getDescription());
		
//		}
//		Optional<Movie> optMovie = movieRepository.findById(movieId);
//		if(optMovie.isPresent()) {
//			return optMovie.get();
//		} else {
//			throw new ResourceNotFoundException("Movie", "movieId", movieId+ "");
//		}
		
//		MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
		MovieDTO movieDTO = mapToDTO(movie);
		return movieDTO;
	}
	
	public List<MovieDTO> getAllMovies(){
		List<Movie> list = movieRepository.findAll();
		return list.stream()
		.map(movie -> mapToDTO(movie))
		.collect(Collectors.toList());
//		return (List) movieRepository.findAll();
	}
	
	
	public MovieDTO updateMovie(Movie newMovie, int movieId) {
		Movie oldMovie = movieRepository.findById(movieId)
				.orElseThrow(() -> new ResourceNotFoundException("Movie", "movieId", ""+movieId));
				
				oldMovie.setTitle(newMovie.getTitle());
				oldMovie.setDescription(newMovie.getDescription());
				oldMovie.setPrice(newMovie.getPrice());
				
				movieRepository.save(oldMovie);
				MovieDTO movieDTO = mapToDTO(oldMovie);
				
				return movieDTO;
	}
	private MovieDTO mapToDTO(Movie movie) {
		return modelMapper.map(movie, MovieDTO.class );
	}
}
