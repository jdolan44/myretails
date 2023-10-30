package edu.scranton.myretails.ui;

import java.util.List;

import edu.scranton.myretails.entity.Customer;

public class CustomerView {

	public static void displayCustomers(List<Customer> customers) {
		if (customers == null || customers.size() == 0) {
			System.out.println("No customers found!\n");
			return;
		}

		displayHeader();
		for (Customer customer : customers) {
			displayRow(customer);
		}
	}

	private static void displayHeader() {
		System.out.println("---------------------------------------------------------------------------");
		System.out.printf("| %-5s | %-30s | %-30s |%n", "id", "name", "email");
		System.out.println("---------------------------------------------------------------------------");
	}

	private static void displayRow(Customer customer) {
		System.out.printf("| %-5d | %-30s | %-30s |%n", 
						  customer.getId(), 
						  customer.getName(), 
						  customer.getEmail());
		System.out.println("---------------------------------------------------------------------------");

	}
	
	

}
