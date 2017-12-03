package crudapp.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crudapp.dao.MovieDao;
import crudapp.entity.Movie;

@Service
public class MovieService {
	MovieDao movieDao;
	
	public MovieService(){
		movieDao = new MovieDao();
	}
	
	public List<Movie> getAllMovies() throws SQLException{
		return movieDao.getAllMovies();
	}
	
	public Movie getMovieById(String imdbId) throws SQLException {
		return movieDao.getMovieById(imdbId);
	}
	
	
	public void updateMovie(Movie movie) throws SQLException{
		movieDao.updateMovie(movie);
	}
	
	public void deleteMovie(String movieId) throws SQLException{
		movieDao.deleteMovie(movieId);
	}
	
	public String addMovie(Movie movie) throws SQLException{
		return movieDao.addMovie(movie);
	}
	
	public List<Movie> getAllMoviesByTitle(String input) throws SQLException{
		return movieDao.getAllMoviesByTitle(input);
	}
	
	public List<Movie> getAllMoviesByYear(String input) throws SQLException{
		return movieDao.getAllMoviesByYear(input);
	}
	
	public List<Movie> getAllMoviesByGenre(String input) throws SQLException{
		return movieDao.getAllMoviesByGenre(input);
	}
	
	public List<Movie> getAllMoviesByLanguage(String input) throws SQLException{
		return movieDao.getAllMoviesByLanguage(input);
	}
	
	public List<Movie> getAllMoviesByCountry(String input) throws SQLException{
		return movieDao.getAllMoviesByCountry(input);
	}
	
}
