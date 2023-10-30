package edu.scranton.myretails.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import edu.scranton.myretails.db.*;

public class MyRetails {

	public static void main(String[] args) throws SQLException{
		System.out.println("MyRetails starting...");
		
		MyRetails retailDriver=new MyRetails();
		
		retailDriver.begin();
	}
	
	public void begin() throws SQLException{
		Scanner scanner=new Scanner(System.in);
		Connection db = initializeConnection(scanner);
		CustomerDAO customerDao = new CustomerDAOImpl(db);
		ProductDAO productDao = new ProductDAOImpl(db);
		
		FrontController controller = new FrontController(customerDao, productDao, scanner);
		controller.begin();
		
		System.out.println("Done!!!");
	}
	
	private Connection initializeConnection(Scanner scanner) throws SQLException{
		String dbname = "dolanj7";
		System.out.print("Username:");
		String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();
		try {
			return DriverManager.getConnection("jdbc:postgresql://lovelace.cs.scranton.edu/" 
					+ dbname, username, password);
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}	
	}
}
