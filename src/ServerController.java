import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.sql.SQLException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler; 
import com.sun.net.httpserver.HttpServer;

/**
 * Server controller displays pages on the web and displays each CRUD method
 * @author NoumaJameel14005341
 *
 */
public class ServerController {
	
	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0); 
		
		// a context is created for each web page that you want to be available on the server
		server.createContext("/create", new CreateFormHandler()); server.setExecutor(null);
		server.createContext("/create", new CreateHandler()); server.setExecutor(null); 
		server.createContext("/retrieve", new RetrieveHandler()); server.setExecutor(null); 
		//server.createContext("/update", new UpdateHandler()); server.setExecutor(null); 
		//server.createContext("/delete", new DeleteHandler()); server.setExecutor(null); 
		server.createContext("/", new MenuHandler()); server.setExecutor(null);
		

		server.start();
		System.out.println("The server is up and running on port 8000");
}
	
	static class CreateFormHandler implements HttpHandler {
		
		public void handle(HttpExchange t) throws IOException {
			
			t.sendResponseHeaders(200, 0);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(t.getResponseBody())); 
			
			// Note that the form will send the user to the /addItem page so we need a handler for this page
			out.write("<html><head></head><body><form method=\"POST\" action=\"/addItem\">"); 
			out.write("ID:<input name=\"n1\"><br>");
			out.write("Title:<input name=\"n2\"><br>"); 
			out.write("Author:<input name=\"n3\"><br>"); 
			out.write("Year:<input name=\"n4\"><br>"); 
			out.write("Edition:<input name=\"n5\"><br>"); 
			out.write("Publisher:<input name=\"n6\"><br>"); 
			out.write("ISBN:<input name=\"n7\"><br>"); 
			out.write("Cover:<input name=\"n8\"><br>"); 
			out.write("Condition:<input name=\"n9\"><br>"); 
			out.write("Price:<input name=\"n10\"><br>");
			out.write("Notes:<input name=\"n11\"><br>");
			out.write("<input type=\"submit\" value=\"Submit\">"); 
			out.write("</form></body></html>");
			out.close();
		}
	} 
	
	/**
	 * Class to CREATE (insert) the new book into the database
	 *
	 */
	static class CreateHandler implements HttpHandler {
		
		public void handle(HttpExchange t) throws IOException {
			
			HashMap<String,String> post = new HashMap<String,String>();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(t.getRequestBody())); 
			String line = "";
			String request = "";
			
			while((line = in.readLine()) != null) {
				request = request + line;
			}
			
			//key=value pairs delimited
			String[] pairs = request.split("&");
			for(int i=0;i<pairs.length;i++) {
				
				String pair = pairs[i]; post.put(URLDecoder.decode(pair.split("=")[0],"UTF-8"),URLDecoder.decode(pair.split("=") [1],"UTF-8"));
			}
			
			// Lists each box for user to input book details
			int ID = Integer.parseInt(post.get("n1"));
			String Title = post.get("n2");
			String Author = post.get("n3");
			int Year = Integer.parseInt(post.get("n4"));	
			int Edition = Integer.parseInt(post.get("n5"));
			String Publisher = post.get("n6");
			String ISBN = post.get("n7");
			String Cover = post.get("n8");
			String Condition = post.get("n9");
			int Price = Integer.parseInt(post.get("n10"));
			String Notes = post.get("n9");
			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(t.getResponseBody())); 
			
			// Add a new Book by calling the method in the DAO
			ArrayList<Book> bookList = new ArrayList();
			BookDAO dao = new BookDAO();
			String table = "<tr>";

			try {
				dao.insertBook();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			try {
				bookList = dao.getAllBooks();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Book d: bookList) {
				System.out.println(d.toString());
				table = table + d.toHtml() + "</tr><tr>";
				
			}

			String response = pageHeader() + table + pageFooter();

			ServerController.writeResponse(t, response); 

		}
	} 	
	
	/**
	 * Handler to RETRIEVE all records from the database
	 *
	 */
	static class RetrieveHandler implements HttpHandler {
	
		public void handle(HttpExchange t) throws IOException {
			
			ArrayList<Book> bookList = new ArrayList();
			BookDAO dao = new BookDAO();
			String table = "<tr>";
			try {
				bookList = dao.getAllBooks();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			for (Book d: bookList) {
				System.out.println(d.toString());
				table = table + d.toHtml() + "</tr><tr>";					
			}

			String response = pageHeader() + table + pageFooter();

			ServerController.writeResponse(t, response);
		}
			
	}
	
	/** 
	 * Handler to show the main menu
	 *
	 */
	static class MenuHandler implements HttpHandler {
		
		public void handle(HttpExchange t) throws IOException {
				
			OutputStream os = null;
			String response = pageHeader() + pageFooter();
			ServerController.writeResponse(t, response.toString()); 
		}
	} 
	
	/**
	 * Writes the response to the httpExchange
	 * @param httpExchange
	 * @param r
	 * @throws IOException
	 */
	public static void writeResponse(HttpExchange httpExchange, String r) throws IOException {
		
		String response  = r;
		httpExchange.sendResponseHeaders(200, response.length()); 
		OutputStream os = httpExchange.getResponseBody(); 
		os.write(response.getBytes());
		os.close();
		
	}
	
	/**
	 * Creates HTML menu 
	 * @return
	 */
	static String htmlMenu() {
		String menuString = "<p><a href='/create'>Create Record</a></p>"
				+ "<p><a href='/retrieve'>Retrieve all Records</a></p>"
				//+ "<p><a href='/update'>Update Record</a></p>"
				//+ "<p><a href='/delete'>Delete Record</a></p>"
				//+ "<p></p>"
				+ "<p><a href='/'>Return to Menu</a></p>";
		return menuString;
	}
	
		/**
		 * This inserts a header welcoming the user
		 * @return
		 */
		public static String pageHeader() {
		
		String header = "<html>"
				+ "<head></head>"
				+ "<body>"
				+ "<h1>Welcome to the Book Library System</h1>"
				+ "<table>";
				
		return header;
		
	}
		/**
		 * This inserts the menu at the bottom of the page
		 * @return
		 */
		public static String pageFooter() {
			
			String footer = "</table>"
					+ htmlMenu()
					+ "<body>"
					+ "</html>";
			
			return footer;
	}
	
}