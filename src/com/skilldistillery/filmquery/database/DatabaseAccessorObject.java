package com.skilldistillery.filmquery.database;

import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

 
  private static String url = "jdbc:mysql://localhost:3306/sdvid";
  //
  	static {
  	    try {
  			Class.forName("com.mysql.jdbc.Driver");
  		} catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	 @Override 
  	  public Film findFilmById(int filmId) throws SQLException {
  		Film newFilm= null;
  	    String user = "student";
  	    String pass = "student";
  	   try{
  		   DatabaseAccessorObject Connection conn = DriverManager.getConnection(url, user, pass);
  	  
  	    String sqltext;
  	    //                1   2           3          4      5
  	    sqltext = "SELECT film.*, language.name from film join language on film.id = language.id where id =?"; /////
  	    PreparedStatement stmt = conn.prepareStatement(sqltext);
  	    stmt.setInt(1, id);
  	    ResultSet rs = stmt.executeQuery();
  	    while (rs.next()) {
  	    	newFilm.setTitle(rs.getString("title"));
  	    	newFilm.setYear(rs.getString("release_year"));
  	    	newFilm.setRating(rs.getString("rating"));
  	    	newFilm.setDescription(rs.getString("description"));
  	    	newFilm.setDescription(rs.getString("language.name"));
  	    	newFilm.setActors(getActorList(id));
  	    	
  	    }
  	    rs.close();stmt.close();conn.close();
  	    return newFilm;

  	   
  }
  	
  	
  	public Film findFilmByKeyword(String keyword) throws SQLException {
  		Film newFilm= null;
  	    String user = "student";
  	    String pass = "student";
  	    try {
			
		Connection conn = DriverManager.getConnection(url, user, pass);
  	    String sqltext;
  	    //                1   2           3          4      5
  	    sqltext = "SELECT * from film where description like '%?%' or title like '%?%' "; /////
  	    PreparedStatement stmt = conn.prepareStatement(sqltext);
  	    stmt.setString(1, keyword);
  	    stmt.setString(2, keyword);
  	    ResultSet rs = stmt.executeQuery();
  	    while (rs.next()) {
  	    	newFilm.setTitle(rs.getString("title"));
  	    	newFilm.setYear(rs.getString("release_year"));
  	    	newFilm.setRating(rs.getString("rating"));
  	    	newFilm.setDescription(rs.getString("description"));
  	    	
  	    }
  	  rs.close(); 
  	  stmt.close(); 
  	  conn.close();
  	  } catch (Exception e) {
  		  e.printStackTrace();
		}
  		
  	    return newFilm;

  	}

  	
  	 public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
  		List<Actor> actors= new ArrayList<>();

  		String user = "student";
  	    String pass = "student";
  	    try {
			
		Connection conn = DriverManager.getConnection(url, user, pass);
  	    String sqltext;
  	   
  	    sqltext = "SELECT actor.first_name, actor.last_name from actor join film_actor on actor.id =film_actor.actor_id"
  	    		+ "join film_actor.film_id=film.id where id =?"; /////
  	    PreparedStatement stmt = conn.prepareStatement(sqltext);
  	    stmt.setInt(1, id);
  	    ResultSet rs = stmt.executeQuery();
  	    while (rs.next()) {
  	    	String first= rs.getString(1);
  	    	String last= rs.getString(2);
  	    	Actor a = new Actor(first, last);
  	    	actors.add(a);
  	    }
  	    rs.close();
  	    stmt.close();
  	    conn.close();
  	  } catch (Exception e) {
			e.printStackTrace();
		}
  	    return actors;
  }
  	    
  	
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
//  	  //...
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
}

