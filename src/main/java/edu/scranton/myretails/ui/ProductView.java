package edu.scranton.myretails.ui;

import java.util.List;

import edu.scranton.myretails.entity.Product;

public class ProductView {

	public static void displayProducts(List<Product> products) {
		if (products == null || products.size() == 0) {
			System.out.println("No products found!\n");
			return;
		}

		diplayHeader();
		for (Product product : products) {
			diplayRow(product);
		}
	}

	private static void diplayHeader() {
		System.out.println("------------------------------------------------------");
		System.out.printf("| %-5s | %-30s | %9s |%n", "id", "name", "unit_cost");
		System.out.println("------------------------------------------------------");
	}

	private static void diplayRow(Product product) {
		System.out.printf("| %-5s | %-30s | $%8.2f |%n", 
						  product.getId(), 
						  product.getName(), 
						  product.getUnit_cost());
		System.out.println("------------------------------------------------------");

	}

}
