package edu.scranton.myretails.ui;

import java.util.List;
import java.util.Scanner;

import edu.scranton.myretails.db.CustomerDAO;
import edu.scranton.myretails.db.ProductDAO;
import edu.scranton.myretails.entity.Customer;
import edu.scranton.myretails.entity.Product;

public class FrontController {
	public static final int INSERT_CUSTOMER = 1;
	public static final int LIST_CUSTOMERS = 2;
	public static final int VIEW_CUSTOMER_BY_ID = 3;
	public static final int INSERT_PRODUCT = 4;
	public static final int LIST_PRODUCTS = 5;
	public static final int EXIT = 6;
	
	private CustomerDAO customerDAO;
	private ProductDAO productDAO;
	private Scanner scanner;

	public FrontController(CustomerDAO customerDAO, ProductDAO productDAO, Scanner scanner) {
		this.customerDAO = customerDAO;
		this.productDAO = productDAO;
		this.scanner = scanner;
	}

	public void begin() {
		int choice = 0;
		while (choice != EXIT) {
			displayMenu();
			choice = scanner.nextInt();

			switch (choice) {
			case INSERT_CUSTOMER:
				insertCustomer();
				break;
			case LIST_CUSTOMERS:
				listCustomers();
				break;
			case VIEW_CUSTOMER_BY_ID:
				viewCustomerByID();
				break;
			case INSERT_PRODUCT:
				insertProduct();
				break;
			case LIST_PRODUCTS:
				listProducts();
				break;
			case EXIT: break;
			default:
				System.out.println("Not a valid choice! please try again.");
			}
		}

	}
	private void insertCustomer() {
		scanner.nextLine();
		
		System.out.println("Enter the customer name:");
		String name = scanner.nextLine();
		System.out.println("Enter the customer email:");
		String email = scanner.nextLine();
		
		Customer customer = new Customer(name, email);
		
		customerDAO.insertCustomer(customer);
		System.out.println("INSERTED: " + customer);
	}
	
	private void listCustomers() {
		List<Customer> customers=customerDAO.getAllCustomers();
		CustomerView.displayCustomers(customers);
		
	}
	private void viewCustomerByID() {
		System.out.println("Enter the ID of the customer");
		int id=scanner.nextInt();
		Customer customer=customerDAO.getCustomerById(id);
		if(customer!=null) {
			System.out.println(customer);
		}
		else {System.out.println("No User With Such ID.");}
		
	}

	private void insertProduct() {
		scanner.nextLine();

		System.out.println("Enter the product name:");
		String name = scanner.nextLine();
		System.out.println("Enter the product cost:");
		double unit_cost = scanner.nextDouble();

		Product product = new Product(name, unit_cost);

		productDAO.insertProduct(product);
		
		System.out.println("INSERTED: " + product);

	}

	private void listProducts() {
		List<Product> products=productDAO.getAllProducts();
		ProductView.displayProducts(products);
		
	}

	private void displayMenu() {
		System.out.println("-----------------------------\n"
				+"Please choose an option:\n"
				+ "1. Insert New Customer\n"
				+ "2. List All Customers\n"
				+ "3. View Customer by ID\n"
				+ "4. Insert New Product\n"
				+ "5. List All Products\n"
				+ "6. Quit\n");
		
	}
}
