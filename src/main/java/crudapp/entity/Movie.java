package crudapp.entity;

import java.sql.Date;

public class Movie {
	String title, rated;
	int year;
	String releaseDate;
	int runtime;
	String plot;
	String awards;
	String poster;
	int metascore;
	double imdbRating;
	int imdbVotes;
	int imdbID;
	String type;
	String dvdDate;
	String boxOffice;
	String production;
	String website;
	boolean response;
	String[] actors;
	String[] countries;
	String[] directors;
	String[] languages;
	String[] genres;
	String[] writers;
	Rating[] ratings;
	
	
	public String[] getLanguages() {
		return languages;
	}
	public void setLanguages(String[] languages) {
		this.languages = languages;
	}
	public String[] getActors() {
		return actors;
	}
	public void setActors(String[] actors) {
		this.actors = actors;
	}
	public String[] getCountries() {
		return countries;
	}
	public void setCountries(String[] countries) {
		this.countries = countries;
	}
	public String[] getDirectors() {
		return directors;
	}
	public void setDirectors(String[] directors) {
		this.directors = directors;
	}
	public String[] getGenres() {
		return genres;
	}
	public void setGenres(String[] genres) {
		this.genres = genres;
	}
	public String[] getWriters() {
		return writers;
	}
	public void setWriters(String[] writers) {
		this.writers = writers;
	}
	public Rating[] getRatings() {
		return ratings;
	}
	public void setRatings(Rating[] ratings) {
		this.ratings = ratings;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRated() {
		return rated;
	}
	public void setRated(String rated) {
		this.rated = rated;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getRuntime() {
		return runtime;
	}
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	public String getAwards() {
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public int getMetascore() {
		return metascore;
	}
	public void setMetascore(int metascore) {
		this.metascore = metascore;
	}
	public double getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}
	public int getImdbVotes() {
		return imdbVotes;
	}
	public void setImdbVotes(int imdbVotes) {
		this.imdbVotes = imdbVotes;
	}
	public int getImdbID() {
		return imdbID;
	}
	public void setImdbID(int imdbID) {
		this.imdbID = imdbID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDvdDate() {
		return dvdDate;
	}
	public void setDvdDate(String dvdDate) {
		this.dvdDate = dvdDate;
	}
	public String getBoxOffice() {
		return boxOffice;
	}
	public void setBoxOffice(String boxOffice) {
		this.boxOffice = boxOffice;
	}
	public String getProduction() {
		return production;
	}
	public void setProduction(String production) {
		this.production = production;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public boolean isResponse() {
		return response;
	}
	public void setResponse(boolean response) {
		this.response = response;
	}
	
	
	
}
