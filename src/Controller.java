import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Scanner;

import com.booklib.menu.BookMenu;
import com.sun.net.httpserver.HttpExchange;


/**
 * This calls each method in the BookDAO when the appropriate option is selected
 * @author NoumaJameel14005341
 *
 */
public class Controller 
{
	public static void main(String[] args) throws SQLException {
		BookMenu bookInventory = new BookMenu();
		BookDAO bookShop = new BookDAO();
		boolean carryOn = true;
		
		// Create the book menu window
		bookInventory.buildMenu();
				
		while (carryOn) {
			
		
			switch (bookInventory.getUserSelection()) {
		
				/**
				 * Case 1 retrieves all books from database and print to console
				 */
				case 1: {
					System.out.println("You chose option 1");
					System.out.println("I'll do something with the model");
					
					for (Book a : bookShop.getAllBooks()) {
						System.out.println("---------------------");
						System.out.println(a);
					}
					System.out.println("End of Data");
					bookInventory.displayMenu();
					break;
				}
				
				/**
				 * Case 2 retrieves a single book from database and print to console
				 */
				case 2: {
					System.out.println("You chose option 2");
					System.out.println("I'll do something with the model");
					
					Book a = bookShop.getBook(0);
					System.out.println(a);
				
					System.out.println("End of Data");
					bookInventory.displayMenu();
					break;	
					}
				
				/**
				 * Case 3 asks user to input book into database
				 */
				case 3: {
					System.out.println("You chose option 3");
					System.out.println("I'll do something with the model");
					
					Book a = bookShop.insertBook();
					
					System.out.println("End of Data");
					bookInventory.displayMenu();
					break;	
					}
				
				/**
				 * Case 4 asks user to input a book and price to update into database
				 */
				case 4: {
					System.out.println("You chose option 4");
					System.out.println("I'll do something with the model");
					
					Book a = bookShop.updatePrice();
					
					System.out.println("End of Data");
					bookInventory.displayMenu();
					break;
				}
				
				/**
				 * Case 5 asks user to input a book to delete from the database
				 */
				case 5: {
					System.out.println("You chose option 5");
					System.out.println("I'll do something with the model");
					
					Book a = bookShop.deleteBook(0);
					
				
					System.out.println("End of Data");
					bookInventory.displayMenu();
					break;	
					}
				
				/**
				 * Case 6 exits the menu
				 */
				case 6: {
					System.out.println("You chose option 6");
					System.out.println("Goodbye!");
					carryOn = false;
					break;
				}
			}
		}
	}
	
}
