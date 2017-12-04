package crudapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import crudapp.entity.Movie;
import crudapp.service.MovieService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value="/movies", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Collection<Movie> getAllMovies() throws SQLException{
        return movieService.getAllMovies();
    }
    
    @RequestMapping(value="/addMovie", method = RequestMethod.POST, consumes = {"application/json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Movie addMovie(@RequestBody Movie movie) throws SQLException{
    	movieService.addMovie(movie);
    	return movie;
    }
    
    @RequestMapping(value="/selectedMovie", method = RequestMethod.GET)
    public @ResponseBody Movie getSelectedMovie(@RequestParam(name="imdbId") int imdbId) throws SQLException {
    	return movieService.getMovieById(imdbId);
    }
    
    @RequestMapping(value="/updateMovie", method = RequestMethod.POST, consumes = {"application/json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Movie updateMovie(@RequestBody Movie movie) throws SQLException{
    	movieService.updateMovie(movie);
    	return movie;
    }
    
    @RequestMapping(value="/deleteMovie", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody int deleteMovie(@RequestParam(name = "imdbId") int imdbId) throws SQLException{
    	movieService.deleteMovie(imdbId);
    	return imdbId;
    }
    
    @RequestMapping(value="/search", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Collection<Movie> getAllMoviesByTitle(@RequestParam(name = "input") String input, @RequestParam(name = "option") String option) throws SQLException{
    		if(option.equals("title"))
    			return movieService.getAllMoviesByTitle(input);
    		else if(option.equals("year"))
    			return movieService.getAllMoviesByYear(input);
    		else if(option.equals("genre"))
    			return movieService.getAllMoviesByGenre(input);
    		else if(option.equals("language"))
    			return movieService.getAllMoviesByLanguage(input);
    		else if(option.equals("country"))
    			return movieService.getAllMoviesByCountry(input);
    		else if(option.equals("actor"))
    			return movieService.getAllMoviesByActor(input);
    		
    		else return null;
    	
    }
    
    
    
}