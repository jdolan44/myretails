package edu.scranton.myretails.db;


import java.util.List;

import edu.scranton.myretails.entity.PurchaseOrder;

public interface PurchaseOrderDAO {
	PurchaseOrder insertPurchaseOrder(PurchaseOrder order);
	PurchaseOrder getOrderByPOID(int po_id);

	void updateTotalCost(int po_id, double total_cost);
	
	List<PurchaseOrder> getOrdersByCustomerID(int cust_id);
	List<PurchaseOrder> getAllOrders();
}
