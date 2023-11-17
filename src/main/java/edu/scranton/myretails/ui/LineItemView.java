package edu.scranton.myretails.ui;

import java.util.List;

import edu.scranton.myretails.db.ProductDAO;
import edu.scranton.myretails.entity.LineItem;
import edu.scranton.myretails.entity.Product;

public class LineItemView {

	public static void displayItems(List<LineItem> items, ProductDAO productDao) {
		if (items == null || items.size() == 0) {
			System.out.println("No line items found!\n");
			return;
		}

		diplayHeader();
		for (LineItem item : items) {
			diplayRow(item, productDao);
		}		
	}

	private static void diplayHeader() {
		System.out.println("-----------------------------------------------------------------");
		System.out.printf("| %-7s | %-7s | %-30s | %-8s |%n", "line_no", "prod_id", "prod_name", "quantity");
		System.out.println("-----------------------------------------------------------------");
	}

	private static void diplayRow(LineItem item, ProductDAO productDao) {
		Product prod=productDao.getProductByID(item.getProd_id());
		System.out.printf("| %-7d | %-7d | %-30s | %-8d |%n", 
				item.getLine_no(),
				item.getProd_id(), 
				prod.getName(), 
				item.getQuantity());
		System.out.println("-----------------------------------------------------------------");
	}

}
