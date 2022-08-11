package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	
	List<Movie> findAllByDescription(String desc);
	List<Movie> findAllByPriceBetween(Integer price1, Integer price2);

	// JPQL Query
//	@Query(value = "SELECT m FROM MOVIE m where m.price >= :price")
//	List<Movie> getAllMoviesByPrice(@Param("price") int price);
	
	//Native Query
	@Query(value = "SELECT * from movie where m.price >= :price", nativeQuery = true)
	List<Movie> getAllMoviesByPrice(@Param("price") int price);
}
