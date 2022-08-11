package com.example.demo.payload;


public class MovieDTO {
	
	private int movieId;
	private String title;
	private String description;

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

	public MovieDTO(int movieId, String title, String description) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.description = description;
	}

	public MovieDTO() {
		super();
	}

}
