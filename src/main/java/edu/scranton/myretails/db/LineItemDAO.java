package edu.scranton.myretails.db;

import java.util.List;

import edu.scranton.myretails.entity.LineItem;

public interface LineItemDAO {
	LineItem insertLineItem(LineItem item);

	List<LineItem> getItemsByPOID(int po_id);
}
