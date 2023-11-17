package edu.scranton.myretails.entity;

public class LineItem {
	private int po_id;
	private int line_no;
	private int prod_id;
	private int quantity;
	
	public LineItem(int po_id, int line_no, int prod_id, int quantity) {
		super();
		this.po_id = po_id;
		this.line_no = line_no;
		this.prod_id = prod_id;
		this.quantity = quantity;
	}

	public int getPo_id() {
		return po_id;
	}

	public void setPo_id(int po_id) {
		this.po_id = po_id;
	}

	public int getLine_no() {
		return line_no;
	}

	public void setLine_no(int line_no) {
		this.line_no = line_no;
	}

	public int getProd_id() {
		return prod_id;
	}

	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "[po_id=" + po_id + 
			   ", line_no=" + line_no + 
			   ", prod_id=" + prod_id + 
			   ", quantity=" + quantity +
			   "]";
	}
	
	
}
