package edu.scranton.myretails.db;

import java.util.List;
import edu.scranton.myretails.entity.Product;

public interface ProductDAO {
	Product insertProduct(Product product);
	List<Product> getAllProducts();
	Product getProductByID(int prod_id);
}
