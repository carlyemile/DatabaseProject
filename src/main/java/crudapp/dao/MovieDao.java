package crudapp.dao;

import java.sql.Connection;
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

@Repository
public class MovieDao {
	 private Connection connect() {
	        // SQLite connection string
	        String url = "jdbc:sqlite:movies.sqlite";
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
	 class MovieRowMapper{
		public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
			Movie movie = new Movie();
			movie.setImdbID(rs.getString("ImdbID"));
			//add rest of attributes
			return movie;
		}

	}
	 
	public List<Movie> getAllMovies() throws SQLException{
		Connection conn = this.connect();
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM movie";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		MovieRowMapper rowMapper = new MovieRowMapper();
		while(rs.next()){
			movies.add(rowMapper.mapRow(rs, 0));
		}
		return movies;
		
	}
	
	public String addMovie(Movie movie) throws SQLException{
		Connection conn = this.connect();
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "insert into movies values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(0,movie.getTitle());
		pstmt.setInt(1,movie.getYear());
		pstmt.setString(2,movie.getRated());
		pstmt.setDate(3,movie.getReleaseDate());
		pstmt.setString(4,movie.getRuntime());
		pstmt.setString(5,movie.getPlot());
		pstmt.setString(6,movie.getPoster());
		pstmt.setInt(7,movie.getMetascore());
		pstmt.setDouble(8,movie.getImdbRating());
		pstmt.setInt(9,movie.getImdbVotes());
		pstmt.setString(10,movie.getType());
		pstmt.setDate(11,movie.getDvdDate());
		pstmt.setInt(12,movie.getBoxOffice());
		pstmt.setString(13,movie.getProduction());
		pstmt.setString(14,movie.getWebsite());

		pstmt.executeUpdate();
		
		String sql2 = "select imdbId from movies where title = "+movie.getTitle();
		PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		return pstmt2.executeQuery().getString("imdbId");
		
	}
	
	public void updateMovie(Movie movie) throws SQLException{
		Connection conn = this.connect();
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "update movies set title = ?, year = ?, mpaa_rating = ?, released = ?, runtime = ?, plot = ?, poster = ?, metascore = ?,imdbrating = ?, imdbvotes = ?, mtype = ?, dvd = ?, boxoffice = ?, production = ?,website = ? where imdbId = ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(0,movie.getTitle());
		pstmt.setInt(1,movie.getYear());
		pstmt.setString(2,movie.getRated());
		pstmt.setDate(3,movie.getReleaseDate());
		pstmt.setString(4,movie.getRuntime());
		pstmt.setString(5,movie.getPlot());
		pstmt.setString(6,movie.getPoster());
		pstmt.setInt(7,movie.getMetascore());
		pstmt.setDouble(8,movie.getImdbRating());
		pstmt.setInt(9,movie.getImdbVotes());
		pstmt.setString(10,movie.getType());
		pstmt.setDate(11,movie.getDvdDate());
		pstmt.setInt(12,movie.getBoxOffice());
		pstmt.setString(13,movie.getProduction());
		pstmt.setString(14,movie.getWebsite());
		pstmt.setString(15,movie.getImdbID());

		pstmt.executeUpdate();
		
	}
	
	public void deleteMovie(String imdbId) throws SQLException{
		Connection conn = this.connect();
		String sql = "delete from movie where imdbId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(0, imdbId);
		pstmt.executeUpdate();
	}

	public List<Movie> getAllMoviesByTitle(String keyword) throws SQLException{
		Connection conn = this.connect();
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM movie where title like '%"+keyword+"%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		MovieRowMapper rowMapper = new MovieRowMapper();
		while(rs.next()){
			movies.add(rowMapper.mapRow(rs, 0));
		}
		return movies;
	}
	
	public List<Movie> getAllMoviesByYear(String keyword) throws SQLException{
		Connection conn = this.connect();
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM movie where year = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(0, Integer.parseInt(keyword));
		ResultSet rs = pstmt.executeQuery();
		MovieRowMapper rowMapper = new MovieRowMapper();
		while(rs.next()){
			movies.add(rowMapper.mapRow(rs, 0));
		}
		return movies;
	}
	
	public List<Movie> getAllMoviesByGenre(String keyword) throws SQLException{
		Connection conn = this.connect();
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM movie, movie_genre where movie.imdbId = movie_genre.imdbId and genre like '%"+keyword+"%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		MovieRowMapper rowMapper = new MovieRowMapper();
		while(rs.next()){
			movies.add(rowMapper.mapRow(rs, 0));
		}
		return movies;
	}
	
	public List<Movie> getAllMoviesByLanguage(String keyword) throws SQLException{
		Connection conn = this.connect();
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM movie, movie_genre where movie.imdbId = movie_genre.imdbId and genre like '%"+keyword+"%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		MovieRowMapper rowMapper = new MovieRowMapper();
		while(rs.next()){
			movies.add(rowMapper.mapRow(rs, 0));
		}
		return movies;
	}
	
	public List<Movie> getAllMoviesByCountry(String keyword) throws SQLException{
		Connection conn = this.connect();
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM country, movie_country where movie.imdbId = movie_country.imdbId and country like '%"+keyword+"%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		MovieRowMapper rowMapper = new MovieRowMapper();
		while(rs.next()){
			movies.add(rowMapper.mapRow(rs, 0));
		}
		return movies;
	}
	
	
	 public static void main(String[] args) throws SQLException{
		 MovieDao app = new MovieDao();
		System.out.println(app.getAllMovies().get(0).getImdbID());
	 }
}
