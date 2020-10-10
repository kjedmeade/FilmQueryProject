package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	//
	static {
  	    try {
  			Class.forName("com.mysql.jdbc.Driver");
  		} catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
	}
  	    
  	
  	@Override
	public Film findFilmById(int filmId) throws SQLException {
  		Film newFilm= null;
  	    String user = "student";
  	    String pass = "student";
  		Connection conn = DriverManager.getConnection(url, user, pass);
  	  
  	    String sqltext;
  	    //                1   2           3          4      5
  	    sqltext = "SELECT film.*, language.name from film join language on film.language_id = language.id where film.id =?"; /////
  	    PreparedStatement stmt = conn.prepareStatement(sqltext);
  	    stmt.setInt(1, filmId);
  	    ResultSet rs = stmt.executeQuery();
  	    while (rs.next()) {
  	    	newFilm = new Film();
			int id2 = rs.getInt("film.id");
			newFilm.setFilmId(id2);
			newFilm.setTitle(rs.getString("film.title"));
  	    	newFilm.setReleaseYear(rs.getShort("film.release_year"));
  	    	newFilm.setRating(rs.getString("film.rating"));
  	    	newFilm.setDescription(rs.getString("film.description"));
  	    	newFilm.setLanguage(rs.getString("language.name"));
  	    	newFilm.setActors(findActorsByFilmId(id2));
  	    }
  	    rs.close();
  	    stmt.close();
  	    conn.close();
  	    return newFilm;

  	   
  }
  	 

	@Override
	public List<Film> findFilmsByKeyword(String keyword) throws SQLException {
		Film newFilm = null;
		List<Film> filmList = new ArrayList<Film>();
		String user = "student";
		String pass = "student";

		Connection conn = DriverManager.getConnection(url, user, pass);
		String sqltext;
		sqltext = "SELECT film.*, language.name from film join language on film.language_id = language.id where description like ? or title like ? "; /////
		PreparedStatement stmt = conn.prepareStatement(sqltext);
		stmt.setString(1, "%" + keyword+ "%");
		stmt.setString(2, "%" + keyword+ "%");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			newFilm = new Film();
			int id2 = rs.getInt("id");
			newFilm.setFilmId(id2);
			newFilm.setTitle(rs.getString("title"));
  	    	newFilm.setReleaseYear(rs.getShort("release_year"));
  	    	newFilm.setRating(rs.getString("rating"));
  	    	newFilm.setDescription(rs.getString("description"));
  	    	newFilm.setLanguage(rs.getString("language.name"));
  	    	newFilm.setActors(findActorsByFilmId(id2));
  	    	filmList.add(newFilm);
		}
		rs.close();
		stmt.close();
		conn.close();

		return filmList;

	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actors = new ArrayList<Actor>();

		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sqltext;

		sqltext = "SELECT actor.id, actor.first_name, actor.last_name from actor "
				+ "join film_actor on actor.id =film_actor.actor_id "
				+ "join film on film_actor.film_id=film.id where film.id =?"; /////
		PreparedStatement stmt = conn.prepareStatement(sqltext);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			
			String first = rs.getString(2);
			String last = rs.getString(3);
			Actor a = new Actor(rs.getInt(1), first, last);
			actors.add(a);
		}
		rs.close();
		stmt.close();
		conn.close();

		return actors;
	}

	

//  	public List<Film> findFilmsByActorId(int actorId) {
//  	  List<Film> films = new ArrayList<>();
//  	  try {
//  	    Connection conn = DriverManager.getConnection(url, user, pass);
//  	    String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
//  	                sql += " rental_rate, length, replacement_cost, rating, special_features "
//  	               +  " FROM film JOIN film_actor ON film.id = film_actor.film_id "
//  	               + " WHERE actor_id = ?";
//  	    PreparedStatement stmt = conn.prepareStatement(sql);
//  	    stmt.setInt(1, actorId);
//  	    ResultSet rs = stmt.executeQuery();
//  	    while (rs.next()) {
//  	      int filmId = rs.getInt(1);
//  	      String title = rs.getString(2);
//  	      String desc = rs.getString(3);
//  	      short releaseYear = rs.getShort(4);
//  	      int langId = rs.getInt(5);
//  	      int rentDur = rs.getInt(6);
//  	      double rate = rs.getDouble(7);
//  	      int length = rs.getInt(8);
//  	      double repCost = rs.getDouble(9);
//  	      String rating = rs.getString(10);
//  	      String features = rs.getString(11);
//  	      Film film = new Film(filmId, title, desc, releaseYear, langId,
//  	                           rentDur, rate, length, repCost, rating, features);
//  	      films.add(film);
//  	    }
//  	    rs.close();
//  	    stmt.close();
//  	    conn.close();
//  	  } catch (SQLException e) {
//  	    e.printStackTrace();
//  	  }
//  	  return films;
//  	}

//  	public Actor findActorById(int actorId) {
//  	  Actor actor = null;
//  
//  	  String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
//  	  PreparedStatement stmt = conn.prepareStatement(sql);
//  	  stmt.setInt(1,actorId);
//  	  ResultSet actorResult = stmt.executeQuery();
//  	  if (actorResult.next()) {
//  	    actor = new Actor(); // Create the object
//  	    // Here is our mapping of query columns to our object fields:
//  	    actor.setId(actorResult.getInt(1));
//  	    actor.setFirstName(actorResult.getString(2));
//  	    actor.setLastName(actorResult.getString(3));
//  	    actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
//  	  }
//  	  //...
//  	  return actor;
//  	}

}
	
