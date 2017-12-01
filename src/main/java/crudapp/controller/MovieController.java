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
    private String addedImdbId;

    @RequestMapping(value="/movies", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Collection<Movie> getAllMovies() throws SQLException{
        return movieService.getAllMovies();
    }
    
    @RequestMapping(value="/addMovie", method = RequestMethod.POST)
    public void addMovie(@RequestParam(name = "attrs") String[] attrs) throws SQLException{
    	Movie movie = new Movie();
    	movie.setTitle(attrs[0]);
    	movie.setYear(Integer.parseInt(attrs[1]));
    	movie.setRated(attrs[2]);
    	movie.setReleaseDate(Date.valueOf(attrs[3]));
    	movie.setRuntime(attrs[4]);
    	movie.setPlot(attrs[5]);
    	movie.setPoster(attrs[6]);
    	movie.setMetascore(Integer.parseInt(attrs[7]));
    	movie.setImdbRating(Double.parseDouble(attrs[8]));
    	movie.setImdbVotes(Integer.parseInt(attrs[9]));
    	movie.setType(attrs[10]);
    	movie.setDvdDate(Date.valueOf(attrs[11]));
    	movie.setBoxOffice(Integer.parseInt(attrs[12]));
    	movie.setProduction(attrs[13]);
    	movie.setWebsite(attrs[14]);
    	movieService.addMovie(movie);
    }
    
    
    
}