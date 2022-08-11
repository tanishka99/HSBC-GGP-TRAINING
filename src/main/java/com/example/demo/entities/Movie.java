package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;



@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int movieId;
	
	@NotBlank(message = "Title cannot be empty")
	private String title;
	
	@NotBlank(message = "Description cannot be blank")
	private String description;
	
	@Min(value = 0)
	@Max(value = 1000)
	private int price;

	public Movie() {

	}

	public Movie(String title, String description, int price) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "\n\t Movie [movieId=" + movieId + ", title=" + title + ", description=" + description + ", price=" + price
				+ "]";
	}

}
