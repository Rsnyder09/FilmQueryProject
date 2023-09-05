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

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	private static final String USERNAME = "student";
	private static final String PASSWORD = "student";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;

		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		String sql = "SELECT * FROM film JOIN language ON language.id = film.language_id WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet filmResult = stmt.executeQuery();

		if (filmResult.next()) {
			film = new Film();
			film.setFilmId(filmResult.getInt("id"));
			film.setTitle(filmResult.getString("title"));
			film.setReleaseYear(filmResult.getShort("release_year"));
			film.setRating(filmResult.getString("rating"));
			film.setDesc(filmResult.getString("description"));
			film.setLangId(filmResult.getInt("language_id"));
			film.setLangName(filmResult.getString("name"));
			film.setRentDur(filmResult.getInt("rental_duration"));
			film.setRate(filmResult.getDouble("rental_rate"));
			film.setLength(filmResult.getInt("length"));
			film.setRepCost(filmResult.getDouble("replacement_cost"));
			film.setFeatures(filmResult.getString("special_features"));
			film.setActors(findActorsByFilmId(filmId));
		}

		filmResult.close();
		stmt.close();
		conn.close();

		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;

		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		String sql = "SELECT * FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);

		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next()) {

			actor = new Actor();
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));
			actor.setFilms(findFilmsByActorId(actorId));

		}

		actorResult.close();
		stmt.close();
		conn.close();

		return actor;
	}

	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "SELECT * FROM film JOIN film_actor ON film.id = film_actor.film_id WHERE actor_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);

				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "SELECT * FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_actor.film_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("film_id");
				String fn = rs.getString("first_name");
				String ln = rs.getString("last_name");
				Actor actor = new Actor(id, fn, ln);

				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;

	}
	
	@Override
	public List<Film> findFilmBySearchWord(String sw) {
		List<Film> films = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "SELECT f.*, l.* FROM film f JOIN language l ON f.language_id = l.id WHERE title LIKE ? OR description Like ? ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + sw + "%");
			stmt.setString(2, "%" + sw + "%");
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				String langName = rs.getString("name");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				List<Actor> actors = new ArrayList<>();
				actors = findActorsByFilmId(filmId);

				Film film = new Film(title, releaseYear, rating, desc, langName, actors);

				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}
}
