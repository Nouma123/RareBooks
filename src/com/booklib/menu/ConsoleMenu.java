package com.booklib.menu;

import java.util.Scanner; 

/**
 *
 * @author NoumaJameel14005341
 *
 */
public class ConsoleMenu {
	
	/**
	 * Holds the items that have been added to the menu
	 */
	private String[] menuItems;
	
	/**
	 * The current size of the menu
	 */
	private int menuSize = 0;
	
	/**
	 * The title appears on a menu
	 */
	private String menuTitle;
	
	/**
	 * The final item of the menu to be added last.  It will allow the user to quit the application.
	 */
	private String terminator;
	
	/**
	 * The maximum allowable size of the menu
	 */
	private final int MAX_MENU_SIZE = 7;
	
	/**
	 * Holds the value that indicates which menu option has been selected
	 */
	private int selection;
	
	/**
	 * Constructor. Initialises the array of MenuItems
	 */
	public ConsoleMenu(){
		
		menuItems = new String[MAX_MENU_SIZE];
	}
	
	
	/**
	 * Adds an item to the end of the menu
	 * 
	 * @param item		The next item to be added to the menu
	 */
	public void addItem(String item) {
		
		this.menuItems[menuSize] = item;
		menuSize++;
		
	}
	
	/**
	 * Adds title
	 * @param string 
	 */
	public void addTitle(String title) {
		
		this.menuTitle = title;
		
	}
	
	/**
	 * Adds a terminator to the end of the menu.  
	 * @param terminator	The terminator for the menu (the final item)
	 */
	public void addTerminator(String terminator) {
		
		this.terminator = terminator;
		this.addItem(this.terminator);
	}
	
	/**
	 * Displays the menu in the correct order
	 */
	public void displayMenu() {
		
		String displayString;
		
		System.out.println(menuTitle);
		
		for(int i = 0; i < menuSize; i++) {

			displayString = (i + 1) + ". " + this.menuItems[i];
			System.out.println(displayString);
		}
	}
	
	/**
	 * Gets the user's selection
	 */
	public int getUserSelection() {
		
		// declare and initialise variables
		int userSelection = 0;
		boolean validSelection = false;
		Scanner sc = new Scanner(System.in);
		
		// get input and validate
		do {
		
			while (!sc.hasNextInt()) {
				System.out.println("Sorry - something went wrong here.");
				System.out.println("Selection must be a number from 1-" + menuSize + ".  Please try again.");
				this.displayMenu();

				sc.next();
			}
			
			userSelection = sc.nextInt();
			
			if ((userSelection < 1) || (userSelection > menuSize)) {
				validSelection = false;
				System.out.println("Selection must be a number from 1-" + menuSize + ".  Please try again.");
				this.displayMenu();
				
			} else {
				validSelection = true;
				System.out.println("Option " + userSelection + " selected.");
			}
			
		} while (!validSelection);
		
		this.selection = userSelection;

		return userSelection;
	}

	/**
	 * Returns book id when entered by user
	 * 
	 * @return
	 */
	
	public int getBook() {
		
		
		return getBook();
		
	}
	
}
