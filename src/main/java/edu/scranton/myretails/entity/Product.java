package edu.scranton.myretails.entity;

public class Product {
	private int id;
	private String name;
	private double unit_cost;
	
	public Product(int id, String name, double unit_cost) {
		this.id = id;
		this.name = name;
		this.unit_cost = unit_cost;
	}
	
	public Product(String name, double unit_cost) {
		this(-1, name, unit_cost);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnit_cost() {
		return unit_cost;
	}

	public void setUnit_cost(double unit_cost) {
		this.unit_cost = unit_cost;
	}

	@Override
	public String toString() {
		return "[" + id + ", " + name + ", " + unit_cost + "]";
	}
	
	
	
	
}
