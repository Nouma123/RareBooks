package com.booklib.menu;

/**
 * 
 * @author NoumaJameel14005341
 *
 */
public class BookMenu extends ConsoleMenu {
	
	/**
	 * Creates the book menu
	 */
	public void buildMenu() {
		
		super.addTitle("Book Inventory - Choose from these options");
		super.addItem("Retrive all books");
		super.addItem("Search for book");
		super.addItem("Insert new book into database");
		super.addItem("Update existing book price details");
		super.addItem("Delete book from database");
		super.addTerminator("Exit");
		super.displayMenu();

	}

}
