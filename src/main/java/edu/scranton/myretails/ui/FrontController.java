package edu.scranton.myretails.ui;

import java.util.List;
import java.util.Scanner;

import edu.scranton.myretails.db.CustomerDAO;
import edu.scranton.myretails.db.ProductDAO;
import edu.scranton.myretails.db.PurchaseOrderDAO;
import edu.scranton.myretails.db.LineItemDAO;
import edu.scranton.myretails.entity.Customer;
import edu.scranton.myretails.entity.LineItem;
import edu.scranton.myretails.entity.Product;
import edu.scranton.myretails.entity.PurchaseOrder;

public class FrontController {
	public static final int INSERT_CUSTOMER = 1;
	public static final int LIST_CUSTOMERS = 2;
	public static final int VIEW_CUSTOMER_BY_ID = 3;
	public static final int INSERT_PRODUCT = 4;
	public static final int LIST_PRODUCTS = 5;
	public static final int PLACE_ORDER = 6;
	public static final int LIST_ORDERS_BY_CUSTOMER_ID = 7;
	public static final int VIEW_ORDER_BY_ID = 8;
	public static final int EXIT = 9;
	
	private CustomerDAO customerDAO;
	private ProductDAO productDAO;
	private PurchaseOrderDAO orderDAO;
	private LineItemDAO itemDAO;
	private Scanner scanner;

	public FrontController(CustomerDAO customerDAO, ProductDAO productDAO, PurchaseOrderDAO orderDAO, LineItemDAO itemDAO, Scanner scanner) {
		this.customerDAO = customerDAO;
		this.productDAO = productDAO;
		this.orderDAO = orderDAO;
		this.itemDAO = itemDAO;
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
			case PLACE_ORDER:
				placeOrder();
				break;
			case LIST_ORDERS_BY_CUSTOMER_ID:
				listOrdersByCustomerID();
				break;
			case VIEW_ORDER_BY_ID:
				viewOrderByID();
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
	
	private void placeOrder() {
		listCustomers();
		System.out.println("Enter ID of customer placing order:");
		int cust_id=scanner.nextInt();
		
		double total_cost=0;
		PurchaseOrder order=new PurchaseOrder(cust_id, total_cost);
		orderDAO.insertPurchaseOrder(order);
		int po_id = order.getId();
		
		int item_no=1;
		int prod_id;
		
		do {
			listProducts();
			System.out.println("Enter the product ID for line item " + item_no + ":");
			prod_id=scanner.nextInt();
			
			if(prod_id != -1) {
				System.out.println("Enter the quantity:");
				int quantity = scanner.nextInt();
				
				Product p = productDAO.getProductByID(prod_id);
				total_cost += (p.getUnit_cost() * quantity);
				LineItem lineitem = new LineItem(po_id, item_no, prod_id, quantity);
				itemDAO.insertLineItem(lineitem);	
			}
			item_no++;
		}while(prod_id !=-1);
		orderDAO.updateTotalCost(po_id, total_cost);
	}
	
	private void listOrdersByCustomerID() {
		listCustomers();
		System.out.println("Enter the customer ID: ");
		int cust_id=scanner.nextInt();
		List<PurchaseOrder> orders = orderDAO.getOrdersByCustomerID(cust_id);
		
		PurchaseOrderView.displayPurchaseOrders(orders);
	}
	
	private void viewOrderByID() {
		List<PurchaseOrder> orders=orderDAO.getAllOrders();
		PurchaseOrderView.displayPurchaseOrdersWithCustID(orders);
		
		System.out.println("Enter the purchase order ID:");
		int po_id = scanner.nextInt();
		
		PurchaseOrder order=orderDAO.getOrderByPOID(po_id);
		PurchaseOrderView.displayPurchaseOrder(order);
		
		List<LineItem> items=itemDAO.getItemsByPOID(po_id);
		LineItemView.displayItems(items, productDAO);
	}
	
	private void displayMenu() {
		System.out.println("-----------------------------\n"
				+"Please choose an option:\n"
				+ "1. Insert New Customer\n"
				+ "2. List All Customers\n"
				+ "3. View Customer by ID\n"
				+ "4. Insert New Product\n"
				+ "5. List All Products\n"
				+ "6. Place Purchase Order\n"
				+ "7. List All Purchase Orders by Customer ID\n"
				+ "8. View Purchase Order Details by Order ID\n"
				+ "9. Quit\n");
		
	}
}
