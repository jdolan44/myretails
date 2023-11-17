package edu.scranton.myretails.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchaseOrder {
	private int id;
	private int cust_id;
	private String o_date;
	private double total_cost;
	
	public PurchaseOrder(int id, int cust_id, String o_date, double total_cost) {
		this.id = id;
		this.cust_id = cust_id;
	    this.o_date = o_date;
	    this.total_cost = total_cost;
	}
	
	public PurchaseOrder(int cust_id, double total_cost) {
		this(-1, cust_id, getCurrentDate(), total_cost);
	}

	private static String getCurrentDate() {
		Date date = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    return formatter.format(date);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public String getO_date() {
		return o_date;
	}

	public void setO_date(String o_date) {
		this.o_date = o_date;
	}

	public double getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}
	
	@Override
	public String toString() {
		return "[id=" + id + 
			   ", cust_id=" + cust_id + 
			   ", o_date=" + o_date + 
			   ", total_cost=" + total_cost
				+ "]";
	}
	
}
