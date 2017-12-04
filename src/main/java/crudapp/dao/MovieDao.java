package crudapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crudapp.dao.MovieDao.MovieRowMapper;
import crudapp.entity.Movie;
import crudapp.entity.Rating;

@Repository
public class MovieDao {
	private Connection conn;
	 public MovieDao() {
	        // SQLite connection string
	        String url = "jdbc:sqlite:movies.sqlite";
	         conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 class MovieRowMapper{
		public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
			Movie movie = new Movie();
			movie.setImdbID(rs.getInt("imdbID"));
			movie.setTitle(rs.getString("title"));
	    	movie.setYear(rs.getInt("year"));
	    	movie.setRated(rs.getString("mpaa_rating"));
	    	movie.setReleaseDate(rs.getString("released"));
	    	movie.setRuntime(rs.getInt("runtime"));
	    	movie.setPlot(rs.getString("plot"));
	    	movie.setPoster(rs.getString("poster"));
	    	movie.setMetascore(rs.getInt("metascore"));
	    	movie.setImdbRating(rs.getDouble("imdbrating"));
	    	movie.setImdbVotes(rs.getInt("imdbvotes"));
	    	movie.setType(rs.getString("mtype"));
	    	movie.setDvdDate(rs.getString("dvd"));
	    	movie.setBoxOffice(rs.getString("boxoffice"));
	    	movie.setProduction(rs.getString("production"));
	    	movie.setWebsite(rs.getString("website"));
	    	movie.setActors(getActors(movie.getImdbID()));
	    	movie.setCountries(getCountries(movie.getImdbID()));
	    	movie.setDirectors(getDirectors(movie.getImdbID()));
	    	movie.setGenres(getGenres(movie.getImdbID()));
	    	movie.setLanguages(getLanguages(movie.getImdbID()));
	    	movie.setWriters(getWriters(movie.getImdbID()));
	    	movie.setRatings(getRatings(movie.getImdbID()));

			return movie;
		}

	}
	 
	public List<Movie> getAllMovies() throws SQLException{
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM movie";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		MovieRowMapper rowMapper = new MovieRowMapper();
		while(rs.next()){
			movies.add(rowMapper.mapRow(rs, 0));
		}
		pstmt.close();

		return movies;
		
	}
	
	public Movie getMovieById(int imdbId) throws SQLException {
		String sql = "SELECT * FROM movie where imdbId =?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, imdbId);
		ResultSet rs = pstmt.executeQuery();
		MovieRowMapper rowMapper = new MovieRowMapper();
		while(rs.next())
		return rowMapper.mapRow(rs, 0);
		
		pstmt.close();
		return null;
	}
	
	public void addMovie(Movie movie) throws SQLException{
		String sql = "insert into movie (title, year, mpaa_rating, released, runtime, plot, poster, metascore, imdbrating, imdbvotes, mtype,"
				+ " dvd, boxoffice, production, website) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,movie.getTitle());
		pstmt.setInt(2,movie.getYear());
		pstmt.setString(3,movie.getRated());
		pstmt.setString(4,movie.getReleaseDate());
		pstmt.setInt(5,movie.getRuntime());
		pstmt.setString(6,movie.getPlot());
		pstmt.setString(7,movie.getPoster());
		pstmt.setInt(8,movie.getMetascore());
		pstmt.setDouble(9,movie.getImdbRating());
		pstmt.setInt(10,movie.getImdbVotes());
		pstmt.setString(11,movie.getType());
		pstmt.setString(12,movie.getDvdDate());
		pstmt.setString(13,movie.getBoxOffice());
		pstmt.setString(14,movie.getProduction());
		pstmt.setString(15,movie.getWebsite());

		pstmt.executeUpdate();
		
		String sql2 = "select imdbId from movie where title = ?";
		pstmt = conn.prepareStatement(sql2);
		pstmt.setString(1, movie.getTitle());
		ResultSet rs = pstmt.executeQuery();
		int imdbId = rs.getInt("imdbId");
		
		pstmt.close();
		addActors(movie, imdbId);
		addCountries(movie, imdbId);
		addDirectors(movie, imdbId);
		addGenres(movie, imdbId);
		addLanguages(movie, imdbId);
		addWriters(movie, imdbId);
		addRatings(movie, imdbId);

		
	}
	
	public void updateMovie(Movie movie) throws SQLException{
		String sql = "update movie set title = ?, year = ?, mpaa_rating = ?, released = ?, runtime = ?, plot = ?, poster = ?, metascore = ?,imdbrating = ?, imdbvotes = ?, mtype = ?, dvd = ?, boxoffice = ?, production = ?,website = ? where imdbId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,movie.getTitle());
		pstmt.setInt(2,movie.getYear());
		pstmt.setString(3,movie.getRated());
		pstmt.setString(4,movie.getReleaseDate());
		pstmt.setInt(5,movie.getRuntime());
		pstmt.setString(6,movie.getPlot());
		pstmt.setString(7,movie.getPoster());
		pstmt.setInt(8,movie.getMetascore());
		pstmt.setDouble(9,movie.getImdbRating());
		pstmt.setInt(10,movie.getImdbVotes());
		pstmt.setString(11,movie.getType());
		pstmt.setString(12,movie.getDvdDate());
		pstmt.setString(13,movie.getBoxOffice());
		pstmt.setString(14,movie.getProduction());
		pstmt.setString(15,movie.getWebsite());
		pstmt.setInt(16,movie.getImdbID());
		pstmt.executeUpdate();
		pstmt.close();
		addGenres(movie, movie.getImdbID());
		addCountries(movie, movie.getImdbID());
		addDirectors(movie, movie.getImdbID());
		addGenres(movie, movie.getImdbID());
		addLanguages(movie, movie.getImdbID());
		addWriters(movie, movie.getImdbID());
		addRatings(movie, movie.getImdbID());

		
		
	}
	
	public void addActors(Movie movie, int imdbId) throws SQLException {
		for(String actor: movie.getActors()) {
		String sql = "insert into actor (actorName) values (?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, actor);
		pstmt.executeUpdate();
		
		String sql2 = "select id from actor where actorName = ?";
		pstmt = conn.prepareStatement(sql2);
		pstmt.setString(1, actor);
		ResultSet rs = pstmt.executeQuery();
		int actorId = rs.getInt("id");
		
		String sql3 = "insert into movieActor (actorId,imdbId) values (?,?)";
		pstmt = conn.prepareStatement(sql3);
		pstmt.setInt(1, actorId);
		pstmt.setInt(2, imdbId);
		pstmt.executeUpdate();
		
		pstmt.close();
		}
	}
	
	public void addCountries(Movie movie, int imdbId) throws SQLException {
		
		
		for(String country: movie.getCountries()) {
		String sql = "insert into country (country) values (?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, country);
		pstmt.executeUpdate();
		
		String sql2 = "select id from country where country = ?";
		pstmt = conn.prepareStatement(sql2);
		pstmt.setString(1, country);
		ResultSet rs = pstmt.executeQuery();
		int countryId = rs.getInt("id");
		
		String sql3 = "insert into movieCountry (countryId,imdbId) values (?,?)";
		pstmt = conn.prepareStatement(sql3);
		pstmt.setInt(1, countryId);
		pstmt.setInt(2, imdbId);
		pstmt.executeUpdate();
		pstmt.close();
		}
	}
	
	public void addDirectors(Movie movie, int imdbId) throws SQLException {
		
		
		for(String director: movie.getDirectors()) {
		String sql = "insert into director (directorName) values (?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, director);
		pstmt.executeUpdate();
		
		String sql2 = "select id from director where directorName = ?";
		pstmt = conn.prepareStatement(sql2);
		pstmt.setString(1, director);
		ResultSet rs = pstmt.executeQuery();
		int directorId = rs.getInt("id");
		
		String sql3 = "insert into movieDirector (directorId,imdbId) values (?,?)";
		pstmt = conn.prepareStatement(sql3);
		pstmt.setInt(1, directorId);
		pstmt.setInt(2, imdbId);
		pstmt.executeUpdate();
		pstmt.close();
		}
	}
	
	public void addGenres(Movie movie, int imdbId) throws SQLException {
		
		
		for(String genre: movie.getGenres()) {
		String sql = "insert into genre (genre) values (?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, genre);
		pstmt.executeUpdate();
		
		String sql2 = "select id from genre where genre = ?";
		pstmt = conn.prepareStatement(sql2);
		pstmt.setString(1, genre);
		ResultSet rs = pstmt.executeQuery();
		int genreId = rs.getInt("id");
		
		String sql3 = "insert into movieGenre (genreId,imdbId) values (?,?)";
		pstmt = conn.prepareStatement(sql3);
		pstmt.setInt(1, genreId);
		pstmt.setInt(2, imdbId);
		pstmt.executeUpdate();
		pstmt.close();
		}
	}
	
	public void addLanguages(Movie movie, int imdbId) throws SQLException {
		
		
		for(String language: movie.getLanguages()) {
		String sql = "insert into language (language) values (?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, language);
		pstmt.executeUpdate();
		
		String sql2 = "select id from language where language = ?";
		pstmt = conn.prepareStatement(sql2);
		pstmt.setString(1, language);
		ResultSet rs = pstmt.executeQuery();
		int languageId = rs.getInt("id");
		
		String sql3 = "insert into movieLanguage (languageId,imdbId) values (?,?)";
		pstmt = conn.prepareStatement(sql3);
		pstmt.setInt(1, languageId);
		pstmt.setInt(2, imdbId);
		pstmt.executeUpdate();
		pstmt.close();
		}
	}
	
	public void addWriters(Movie movie, int imdbId) throws SQLException {
		
		
		for(String writer: movie.getWriters()) {
		String sql = "insert into writer (writerName) values (?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, writer);
		pstmt.executeUpdate();
		
		String sql2 = "select id from writer where writerName = ?";
		pstmt = conn.prepareStatement(sql2);
		pstmt.setString(1, writer);
		ResultSet rs = pstmt.executeQuery();
		int writerId = rs.getInt("id");
		
		String sql3 = "insert into moviewriter (writerId,imdbId) values (?,?)";
		pstmt = conn.prepareStatement(sql3);
		pstmt.setInt(1, writerId);
		pstmt.setInt(2, imdbId);
		pstmt.executeUpdate();
		pstmt.close();
		}
	}
	
	public void addRatings(Movie movie, int imdbId) throws SQLException {
		
		
		for(Rating rating: movie.getRatings()) {
		String sql = "insert into rating (value, source, imdbId) values (?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, rating.getValue());
		pstmt.setString(2, rating.getSource());
		pstmt.setInt(3, imdbId);
		pstmt.executeUpdate();
		pstmt.close();
		}
	}
	
	public void deleteMovie(int imdbId) throws SQLException{
		
		String sql = "delete from movie where imdbId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, imdbId);
		pstmt.executeUpdate();
		pstmt.close();
	}

	public List<Movie> getAllMoviesByTitle(String keyword) throws SQLException{
		
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM movie where title like '%"+keyword+"%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		MovieRowMapper rowMapper = new MovieRowMapper();
		while(rs.next()){
			movies.add(rowMapper.mapRow(rs, 0));
		}
		pstmt.close();
		return movies;
	}
	
	public List<Movie> getAllMoviesByYear(String keyword) throws SQLException{
		
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM movie where year like '%"+keyword+"%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		MovieRowMapper rowMapper = new MovieRowMapper();
		while(rs.next()){
			movies.add(rowMapper.mapRow(rs, 0));
		}
		pstmt.close();
		return movies;
	}
	
	public List<Movie> getAllMoviesByGenre(String keyword) throws SQLException{
		
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM movie, genre, movieGenre where movie.imdbId = movieGenre.imdbId and genre.id = movieGenre.genreId and genre like '%"+keyword+"%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		MovieRowMapper rowMapper = new MovieRowMapper();
		while(rs.next()){
			movies.add(rowMapper.mapRow(rs, 0));
		}
		pstmt.close();
		return movies;
	}
	
	public List<Movie> getAllMoviesByLanguage(String keyword) throws SQLException{
		
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM movie, language, movieLanguage where movie.imdbId = movieLanguage.imdbId and language.id = movieLanguage.languageId and language like '%"+keyword+"%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		MovieRowMapper rowMapper = new MovieRowMapper();
		while(rs.next()){
			movies.add(rowMapper.mapRow(rs, 0));
		}
		pstmt.close();
		return movies;
	}
	
	public List<Movie> getAllMoviesByCountry(String keyword) throws SQLException{
		
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM movie, country, movieCountry where movie.imdbId = movieCountry.imdbId and country.id = movieCountry.countryId and country like '%"+keyword+"%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		MovieRowMapper rowMapper = new MovieRowMapper();
		while(rs.next()){
			movies.add(rowMapper.mapRow(rs, 0));
		}
		pstmt.close();
		return movies;
	}
	
	public List<Movie> getAllMoviesByActor(String keyword) throws SQLException{
		
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM movie, actor, movieActor where movie.imdbId = movieActor.imdbId and actor.id = movieActor.actorId and actorName like '%"+keyword+"%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		MovieRowMapper rowMapper = new MovieRowMapper();
		while(rs.next()){
			movies.add(rowMapper.mapRow(rs, 0));
		}
		pstmt.close();
		return movies;
	}
	
	public String[] getActors(int imdbId) throws SQLException {
		
		List<String> actors = new ArrayList<String>();
		String sql = "SELECT actorName FROM movie, movieActor, actor where movie.imdbId = movieActor.imdbId and actor.id = movieActor.actorId and movie.imdbId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, imdbId);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			actors.add(rs.getString("actorName"));
		}
		pstmt.close();
		return actors.toArray(new String[actors.size()]);
	}
	
	public String[] getCountries(int imdbId) throws SQLException {
		
		List<String> countries = new ArrayList<String>();
		String sql = "SELECT country FROM movie, movieCountry, country where movie.imdbId = movieCountry.imdbId and country.id = movieCountry.countryId and movie.imdbId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, imdbId);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			countries.add(rs.getString("country"));
		}
		pstmt.close();
		return countries.toArray(new String[countries.size()]);
	}
	
	public String[] getDirectors(int imdbId) throws SQLException {
		
		List<String> directors = new ArrayList<String>();
		String sql = "SELECT directorName FROM movie, movieDirector, director where movie.imdbId = movieDirector.imdbId and director.id = movieDirector.directorId and movie.imdbId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, imdbId);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			directors.add(rs.getString("directorName"));
		}
		pstmt.close();
		return directors.toArray(new String[directors.size()]);
	}
	
	public String[] getGenres(int imdbId) throws SQLException {
		
		List<String> genres = new ArrayList<String>();
		String sql = "SELECT genre FROM movie, movieGenre, genre where movie.imdbId = movieGenre.imdbId and genre.id = movieGenre.genreId and movie.imdbId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, imdbId);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			genres.add(rs.getString("genre"));
		}
		pstmt.close();
		return genres.toArray(new String[genres.size()]);
	}
	
	public String[] getLanguages(int imdbId) throws SQLException {
		
		List<String> languages = new ArrayList<String>();
		String sql = "SELECT language FROM movie, movieLanguage, language where movie.imdbId = movieLanguage.imdbId and language.id = movieLanguage.languageId and movie.imdbId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, imdbId);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			languages.add(rs.getString("language"));
		}
		pstmt.close();
		return languages.toArray(new String[languages.size()]);
	}
	
	public String[] getWriters(int imdbId) throws SQLException {
		
		List<String> writers = new ArrayList<String>();
		String sql = "SELECT writerName FROM movie, movieWriter, writer where movie.imdbId = movieWriter.imdbId and writer.id = movieWriter.writerId and movie.imdbId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, imdbId);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			writers.add(rs.getString("writerName"));
		}
		pstmt.close();
		return writers.toArray(new String[writers.size()]);
	}
	
	public Rating[] getRatings(int imdbId) throws SQLException {
		
		List<Rating> ratings = new ArrayList<Rating>();
		String sql = "SELECT source, value FROM movie, rating where movie.imdbId = rating.imdbId and movie.imdbId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, imdbId);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			Rating rating = new Rating();
			rating.setSource(rs.getString("source"));
			rating.setValue(rs.getInt("value"));
			ratings.add(rating);
		}
		pstmt.close();
		return ratings.toArray(new Rating[ratings.size()]);
	}
	
}
