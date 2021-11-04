import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The DAO stores each method, ready to be called by the Controller
 * It stores methods to create, retrieve, update and delete data
 * @author NoumaJameel14005341
 *
 */
public class BookDAO {
	
	final String url = "jdbc:sqlite:books.sqlite";
	/**
	 * Creates and opens a connection to the database
	 * 
	 * @return	<code>connection</code>	a connection to the Book database
	 */
	private static Connection getDBConnection() {
		
		// Create a connection and specify the database name
		Connection conn = null;
		final String DATABASE_NAME = "books.sqlite";
		
		//Create the url for the database
		String url = "jdbc:sqlite:" + DATABASE_NAME;  // the url of the database

		
		// initialise the JDBC driver class
		try {
			Class.forName("org.sqlite.JDBC");  // initialises the class named "org.sqlite.JDBC" 
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Oh dear!  Have you named the database driver class correctly?");
			System.out.println(e.getMessage());
		}
		
		// get the connection object of the specified database
		try {
			conn = DriverManager.getConnection(url); // gets the connection for the specified database from the DriverManager class.	
		}
		catch (SQLException e) {
			System.out.println("Ooops! I can't make a connection to the specified database: " + url);
			System.out.println(e.getMessage());
		}
		
		return conn;
		
	}
	
	/**
	 * Gets all Books from the database and places them unordered into an <code>ArrayList</code> of Book objects.
	 * 
	 * @return  bookList - An ArrayList of Book objects.
	 * 
	 * @throws SQLException
	 */
	public ArrayList<Book> getAllBooks() throws SQLException {
		
		Connection dbConnection = null;	// The database connection
		Statement statement = null;		// The statement object executes operations on the database
		ResultSet result = null;		// The ResultSet stores the data returned from the database after executing a query.
		
		String query = "SELECT * FROM books;"; // An SQL Statement (must be correct).
		ArrayList<Book> bookList = new ArrayList<>();

		
		System.out.println("Retrieving all Books...");
		
		try {
			// Connect to the database and run the query putting the result in a ResultSet called result"
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("DBQuery = " + query);
			
			result = statement.executeQuery(query);
			
			// Go through the ResultSet and extract the required information
			while(result.next()) {
				
				int book_id = result.getInt("ID");
				String title = result.getString("Title");
				String author = result.getString("Author");
				int year = result.getInt("Year");
				int edition = result.getInt("Edition");
				String publisher = result.getString("Publisher");
				String isbn = result.getString("ISBN");
				String cover = result.getString("Cover");
				String condition = result.getString("Condition");
				int price = result.getInt("Price");
				String notes = result.getString("Notes");
				
				
				System.out.println("---------------------------------------------------------------------------------------------");
				
				System.out.println("ID: " + book_id + "," + " Title: " + title + "," + " Author: " 
				+ author + "," + " Year: " + year);
				
				System.out.println("---------------------------------------------------------------------------------------------");
				// Add a new Book to the list using the information gathered from the ResultSet
				
				System.out.println("Book ID: " + book_id + '\n' + "Title: " + title + '\n' + "Author: " + author + '\n' + 
						"Year: " + year + '\n' + "Edition: " + edition + '\n' + "Publisher: " + publisher + '\n' + "ISBN: " + isbn + '\n' 
						+ "Cover: " + cover + '\n' + "Condition: " + condition + '\n' + "Price: " + price + '\n' + "Notes: " + notes + '\n');
			
				
				bookList.add(new Book(result.getInt("ID"), result.getString("Title"),
						result.getString("Author"), result.getInt("Year"), result.getInt("Edition"), 
						result.getString("Publisher"), result.getString("ISBN"), result.getString("Cover"), 
						result.getString("Condition"), result.getInt("Price"), result.getString("Notes")));

			}
			
		}
		
		// do the following even if there is an exception
		finally {
		
			if (result != null) { result.close(); }
			if (statement != null) { statement.close(); }
			if (dbConnection != null) { dbConnection.close(); }
		}
	
		return bookList;
		
	}

	/**
	 * Gets a book when searched for
	 * @return 
	 * 
	 **/

	public Book getBook(int id) throws SQLException {
		
		Connection dbConnection = null;	// The database connection
		Statement statement = null;		// The statement object executes operations on the database
		ResultSet result = null;		// The ResultSet stores the data returned from the database after executing a query.
		
		Book tempid = null;
		System.out.println("Enter Book ID:");
		
		Scanner sc = new Scanner(System.in);
		boolean validInput = false;
		int id1 = sc.nextInt();
		
		/**
		 * Query to select a single book from the database
		 * 
		 */
		String query = "SELECT * FROM books WHERE ID=" + id1 + ";";
		
		try {
			// Connect to the database and run the query putting the result in a ResultSet called result"
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("DBQuery = " + query);
			result = statement.executeQuery(query);
			
			// Go through the ResultSet and extract the required information
			if (result.next()) {
				
				int book_id = result.getInt("ID");
				String title = result.getString("Title");
				String author = result.getString("Author");
				int year = result.getInt("Year");
				int edition = result.getInt("Edition");
				String publisher = result.getString("Publisher");
				String isbn = result.getString("ISBN");
				String cover = result.getString("Cover");
				String condition = result.getString("Condition");
				int price = result.getInt("Price");
				String notes = result.getString("Notes");
			
				System.out.println("---------------------------------------------------------------------------------------------");
				
				System.out.println("ID: " + book_id + "," + " Title: " + title + "," + " Author: " 
				+ author + "," + " Year: " + year);
				
				System.out.println("---------------------------------------------------------------------------------------------");
				
				// Add a new Book to the list using the information gathered from the ResultSet
				System.out.println("Book ID: " + book_id + '\n' + "Title: " + title + '\n' + "Author: " + author + '\n' + 
						"Year: " + year + '\n' + "Edition: " + edition + '\n' + "Publisher: " + publisher + '\n' + "ISBN: " + isbn + '\n' 
						+ "Cover: " + cover + '\n' + "Condition: " + condition + '\n' + "Price: " + price + '\n' + "Notes: " + notes + '\n');
				
			}
			
		}
		
		//Do this even if there is an exception
		finally {
			
			if (result != null) { result.close(); }
			if (statement != null) { statement.close(); }
			if (dbConnection != null) { dbConnection.close(); }
		}
		
		return tempid;
	
	}
	
	/**
	 * Inserts new book into database
	 * 
	 */
	
	public Book insertBook() throws SQLException {
		
		Connection dbConnection = null;	// The database connection
		Statement statement = null;		// The statement object executes operations on the database
		
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter(System.getProperty("line.separator"));
		
		System.out.println("Enter the follow information to add a new book:");
		
		System.out.println("Book ID:");
		int book_id = sc.nextInt();
		
		System.out.println("Title:");
		String title = sc.next();
		
		System.out.println("Author:");
		String author = sc.next();
		
		System.out.println("Year:");
		int year = sc.nextInt();
		
		System.out.println("Edition:");
		int edition = sc.nextInt();
		
		System.out.println("Publisher:");
		String publisher = sc.next();
		
		System.out.println("ISBN:");
		String isbn = sc.next();
		
		System.out.println("Cover:");
		String cover = sc.next();
		
		System.out.println("Condition:");
		String condition = sc.next();
		
		System.out.println("Price:");
		int price = sc.nextInt();
		
		System.out.println("Notes:");
		String notes = sc.next();
		
		String query = "INSERT INTO Books (ID, Title, Author, Year, Edition, Publisher, ISBN, Cover, Condition, Price, Notes)"
				+ "VALUES ('" + book_id + "', '" + title + "', '" + author + "', '" + year + "', '" + edition + "', '" + publisher + "', '" + isbn 
				+ "', '" + cover + "', '" + condition + "', '" + price + "', '" + notes + "')";
		
		try {
			/**
			 * Connect to the database and run the query putting the result in a ResultSet called result"
			 */
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			dbConnection.setAutoCommit(false);
			statement.executeUpdate(query);
			dbConnection.commit();
		}
		
		finally {
			if (statement != null) { statement.close(); }
			if (dbConnection != null) { dbConnection.close(); }
		}
		
		return null;

	}
	
	/**
	 * Update price of book
	 * 
	 */
	
	public Book updatePrice () throws SQLException {
		
		Connection dbConnection = null;	// The database connection
		Statement statement = null;		// The statement object executes operations on the database
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the Book ID you'd like to update the price of:");
		int id = sc.nextInt();
		
		System.out.println("Enter the new price:");
		int price = sc.nextInt();
		
		/**
		 * Query to update the set price for a Book using the Book ID
		 * 
		 */
		String query = "UPDATE books SET price = " + price + " WHERE ID = " + id + " ; ";
		
		try {
			// Connect to the database and run the query putting the result in a ResultSet called result"
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			dbConnection.setAutoCommit(false);
			statement.executeUpdate(query);
			dbConnection.commit();
			}
		
			//Do this even if there is an exception
		finally {
			if (statement != null) { statement.close(); }
			if (dbConnection != null) { dbConnection.close(); }
		}
		
		return null;
	}
	
	
	
	/**
	 * Deletes book using the Book ID
	 * 
	 */
	
	public Book deleteBook (int id) throws SQLException {
		
		Connection dbConnection = null;	// The database connection
		Statement statement = null;		// The statement object executes operations on the database
		
		System.out.println("Enter Book ID:");
		
		Scanner sc = new Scanner(System.in);
		id = sc.nextInt();
		
		/**
		 * Query to delete book from database using ID
		 * 
		 */
		String query = "DELETE FROM books WHERE ID = " + id + ";";
		
		try {
			// Connect to the database and run the query putting the result in a ResultSet called result"
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			dbConnection.setAutoCommit(false);
			statement.executeUpdate(query);
			dbConnection.commit();
			}
		
			//Do this even if there is an exception
		finally {
			if (statement != null) { statement.close(); }
			if (dbConnection != null) { dbConnection.close(); }
		}
		
		return null;
	}
	
}
