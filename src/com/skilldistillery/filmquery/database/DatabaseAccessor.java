package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
	 public Film findFilmById(int filmId) throws SQLException;
	 public List<Film> findFilmsByKeyword(String keyword) throws SQLException;
	 public List<Actor> findActorsByFilmId(int filmId) throws SQLException;
	 public Film createFilm(Film film) throws SQLException;
	public boolean deleteFilm(Film findFilmById) throws SQLException;
	 
}
