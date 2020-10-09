package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) throws SQLException {
    FilmQueryApp app = new FilmQueryApp();
    app.test();
//    app.launch();
  }

  private void test() throws SQLException {
    Film film = db.findFilmById(1);
    System.out.println(film);
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
	
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	  boolean keepGoing = true;
		while (keepGoing) {
			printMenu();
			int answer = input.nextInt();
			if (answer>=1 && answer <=3) {
			switch (answer) {
			case 1:
				System.out.println("Please enter film ID:");
				int answer2 = input.nextInt();
				
				try {
					System.out.println(db.getFilmById(answer2).toString());

				} catch (Exception e) {
					System.out.println("Film not found");
				}
				break;
			case 2:
				System.out.println("Enter a keyword:");
				String keyword = input.nextLine();
				try {
					System.out.println(db.findFilmByKeyword(keyword).toString());
				} catch (Exception e) {
					System.out.println("Film not found");
				}
				break;
			case 3:
				System.out.println("Goodbye");
				keepGoing = false;
				break;

			default:
				break;
			}

		}
			else {
				System.out.println("Enter a number between 1 and 3");
			}
		}
  }
		
public void printMenu() {
			System.out.println("Enter a number between 1 and 3:");
			System.out.println("1.Look up a film by its id.");
			System.out.println("2.Look up a film by a search keyword.");
			System.out.println("3.Exit the application");

		}
}}
