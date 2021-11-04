/**
 * The <code>Book</code> holds the information taken from the database about 
 * the Book record identified by the data in the member variable <code>Bookid</code>
 
 * @author NoumaJameel14005341
 *
 */
public class Book {
	
	private int book_id;
	private String title;
	private String author;
	private int year;
	private int edition;
	private String publisher;
	private String isbn;
	private String cover;
	private String condition;
	private int price;
	private String notes;
	
	/**
	 * Constructor loads Book data into the object 
	 */
	public Book(int book_id, String title, String author, int year, int edition, String publisher,
		String isbn, String cover, String condition, int price, String notes) {

	this.book_id = book_id;
	this.title = title;
	this.author = author;
	this.year = year;
	this.edition = edition;
	this.publisher = publisher;
	this.isbn = isbn;
	this.cover = cover;
	this.condition = condition;
	this.price = price;
	this.notes = notes; 
	
	}
	
	/**
	 * Takes the data in the object and creates a String that will display the contents of the object.
	 * 
	 * @return displayString
	 */
	@Override
	public String toString() {
		String displayString = "Book ID: " + this.book_id
			 + "Title: " + this.title
			 + "Author: " + this.author
			 + "Year: " + this.year
			 + "Edition: " + this.edition
			 + "Publisher: " + this.publisher
			 + "ISBN: " + this.isbn
			 + "Cover: " + this.cover
			 + "Condition: " + this.condition
			 + "Price: " + this.price
			 + "Notes: " + this.notes;
	
	return displayString;
	
	}
	
	public String toHtml() {
		return "<td>" + book_id + "</td><td>" 
					  + title + "</td><td>" 
					  + author + "</td><td>" 
					  + year + "</td><td>" 
					  + edition + "</td><td>" 
					  + publisher + "</td><td>" 
					  + isbn + "</td><td>" 
					  + cover + "</td><td>" 
					  + condition + "</td><td>" 
					  + price + "</td><td>" 
					  + notes + "</td>";
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getNote() {
		return notes;
	}

	public void setNote(String note) {
		this.notes = note;
	}
	

}


