package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;
import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		 //app.test();
		app.launch();
	}

//  private void test()throws SQLException {
//    Film film = db.findFilmById(1);
//    Actor actor = db.findActorById(1);
//    System.out.println(film);
//    System.out.println(actor);
//  }

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {

		System.out.println(" ______________________________________");
		System.out.println("|    Welcome to the Film Query App!    |");
		System.out.println("|      Please select a number 1-3      |");
		System.out.println("|--------------------------------------|");
		System.out.println("| 1. Look up a film by its id          |");
		System.out.println("|                                      |");
		System.out.println("| 2.Look up a film by a serch keyword  |");
		System.out.println("|                                      |");
		System.out.println("| 3.Exit                               |");
		System.out.println("|______________________________________|");

		int choice = input.nextInt();

		switch (choice) {
		case 1:
			userLookUpFilmByID(input);
			break;
		 case 2:
			userKeyword(input);
			break;
		 case 3: 
			 exit(input);
			 break;
		default:
			System.out.println("You have entered an invalid number");
			startUserInterface(input);
		}

	}

	private void userLookUpFilmByID(Scanner input) throws SQLException {
		System.out.println("Please enter a film ID: ");
		int userFilmID = input.nextInt();
		Film film = db.findFilmById(userFilmID);
		List<Actor> actors =db.findActorsByFilmId(userFilmID);

		if (film == null) {
			System.out.println("Film could not be found");
			System.out.println();
			userLookUpFilmByID(input);
		} else {
			System.out.println(film);
			startUserInterface(input);

		}
	}
	
	private void userKeyword(Scanner input) throws SQLException {
		System.out.println("Please enter a keyword: ");
		String userKeyWord = input.next();
		List<Film> films = db.findFilmBySearchWord(userKeyWord);
		
		
		if (films.size() == 0) {
			System.out.println("Keyword does not match anything in title or description");
			System.out.println();
			userKeyword(input);
		} else {
				for (Film film : films) {
					System.out.println(film);
				}
			}
		startUserInterface(input);	
		}
	
	private void exit(Scanner input) {
		System.out.println("Goodbye");
		System.exit(0);
	}

	
	
}
