package week_3_task_HOP;

class BookClass{
	    private int bookId;
	    private String title;
	    private String author;
	    private double price;
	    private boolean available;
	    
		public BookClass(int bookId, String title, String author, double price) {
			super();
			this.bookId = bookId;
			this.title = title;
			this.author = author;
			this.price = price;
			this.available = true;
		}

		public int getBookId() {
			return bookId;
		}

		public void setBookId(int bookId) {
			this.bookId = bookId;
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

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public boolean isAvailable() {
			return available;
		}

		public void setAvailable(boolean available) {
			this.available = available;
		}
		
		 public void issueBook() {

		        if (available) {
		            available = false;
		            System.out.println("Book issued successfully.");
		        } else {
		            System.out.println("Book is already issued.");
		        }
		    }

		    
		    public void returnBook() {

		        if (!available) {
		            available = true;
		            System.out.println("Book returned successfully.");
		        } else {
		            System.out.println("Book is already available.");
		        }
		    }

			@Override
			public String toString() {
				return "BookClass [bookId=" + bookId + ", title=" + title + ", author=" + author + ", price=" + price
						+ ", available=" + available + "]";
			}
	    
	    
}
public class Book {
	public static void main(String[] args) {

        BookClass book = new BookClass(101,"Java Programming","James Gosling",599);


        System.out.println(book.toString());

        book.issueBook();

        System.out.println();

        System.out.println(book.toString());

        book.issueBook();

        System.out.println();

        book.returnBook();

        System.out.println(book.toString());

    }

}
