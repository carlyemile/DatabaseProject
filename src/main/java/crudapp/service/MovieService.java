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
	
	
	public void updateMovie(Movie movie) throws SQLException{
		movieDao.updateMovie(movie);
	}
	
	public void deleteMovie(String movieId) throws SQLException{
		movieDao.deleteMovie(movieId);
	}
	
	public void addMovie(Movie movie) throws SQLException{
		movieDao.addMovie(movie);
	}
	
}
