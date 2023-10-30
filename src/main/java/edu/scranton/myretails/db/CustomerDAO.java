package edu.scranton.myretails.db;

import java.util.List;
import edu.scranton.myretails.entity.Customer;

public interface CustomerDAO {
	Customer insertCustomer(Customer customer);
	Customer getCustomerById(int id);
	List<Customer> getAllCustomers();
}
