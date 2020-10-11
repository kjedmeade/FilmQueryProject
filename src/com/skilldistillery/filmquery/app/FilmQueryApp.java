package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) throws SQLException {
    FilmQueryApp app = new FilmQueryApp();
//    app.test();
    app.launch();
  }

//  private void test() throws SQLException {
//    Film film = db.findFilmById(1);
//    System.out.println(film);
//  }

  private void launch() {
    Scanner input = new Scanner(System.in);
	
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	  boolean keepGoing = true;
		outer: while (keepGoing) {
			printMenu();
			int answer=0;
			try {
				 answer = input.nextInt();
			} catch (Exception e) {
				System.out.println("Your entry could not be processed. Please restart the program, and enter a number between 1 and 3. \n");
				break;
			}
			
			input.nextLine();
			if (answer>=1 && answer <=3) {
			switch (answer) {
			case 1:
				System.out.println("Please enter film ID:");
				int answer2 = 0;
						try {
							answer2= input.nextInt();
						} catch (Exception e) {
							System.out.println("Your entry could not be processed.  Please restart the program. Enter film id in the form of an integer. \n");
							break outer;
						}
				
				try {
					System.out.println(db.findFilmById(answer2).toString());

				} catch (Exception e) {
					System.out.println("Film not found");
				}
				break;
			case 2:
				System.out.println("Enter a keyword:");
				String keyword = input.nextLine();
				try {
					System.out.println(db.findFilmsByKeyword(keyword).toString());
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
				System.out.println("Please enter a number between 1 and 3");
			}
		}
  }
		
public void printMenu() {
			System.out.println("Enter a number between 1 and 3:");
			System.out.println("1.Look up a film by its id.");
			System.out.println("2.Look up a film by a search keyword.");
			System.out.println("3.Exit the application");

		}
}

