package edu.scranton.myretails.ui;

import java.util.List;

import edu.scranton.myretails.entity.PurchaseOrder;

public class PurchaseOrderView {
	
	public static void displayPurchaseOrder(PurchaseOrder order) {
		System.out.println(order);
	}

	public static void displayPurchaseOrders(List<PurchaseOrder> orders) {
		if (orders == null || orders.size() == 0) {
			System.out.println("No products found!\n");
			return;
		}

		diplayHeader();
		for (PurchaseOrder order : orders) {
			diplayRow(order);
		}
	}
	
	private static void diplayHeader() {
		System.out.println("-----------------------------------");
		System.out.printf("| %-5s | %-10s | %-10s |%n", "id", "o_date", "total_cost");
		System.out.println("-----------------------------------");
	}

	private static void diplayRow(PurchaseOrder order) {
		System.out.printf("| %-5d | %-10s | $%-9.2f |%n", 
						  order.getId(),
						  order.getO_date(),
						  order.getTotal_cost());
		System.out.println("-----------------------------------");
	}
	
	public static void displayPurchaseOrdersWithCustID(List<PurchaseOrder> orders) {
		if (orders == null || orders.size() == 0) {
			System.out.println("No products found!\n");
			return;
		}

		diplayHeaderWithCustID();
		for (PurchaseOrder order : orders) {
			diplayRowWithCustID(order);
		}
	}
	
	
	private static void diplayHeaderWithCustID() {
		System.out.println("---------------------------------------------");
		System.out.printf("| %-5s | %-7s | %-10s | %-10s |%n", "id","cust_id", "o_date", "total_cost");
		System.out.println("---------------------------------------------");

	}

	private static void diplayRowWithCustID(PurchaseOrder order) {
		System.out.printf("| %-5d | %-7s | %-10s | $%-9.2f |%n", 
						  order.getId(),
						  order.getCust_id(),
						  order.getO_date(),
						  order.getTotal_cost());
		System.out.println("---------------------------------------------");

	}
}
