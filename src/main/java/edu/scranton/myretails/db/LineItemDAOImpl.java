package edu.scranton.myretails.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.scranton.myretails.entity.LineItem;

public class LineItemDAOImpl implements LineItemDAO {
	private Connection db;
	
	public LineItemDAOImpl(Connection db) {
		this.db = db;
	}

	@Override
	public LineItem insertLineItem(LineItem item) {
		LineItem result = null;
		try (PreparedStatement ps = generateInsertLineItemStatement(item)) {
			ps.executeUpdate();
			return item;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
	}

	private PreparedStatement generateInsertLineItemStatement(LineItem item) 
			throws SQLException{
		String sql="INSERT INTO line_item(po_id, line_no, prod_id, quantity)"
				 + "VALUES(?, ?, ?, ?)";
		PreparedStatement ps = db.prepareStatement(sql);
		ps.setInt(1, item.getPo_id());
		ps.setInt(2, item.getLine_no());
		ps.setInt(3, item.getProd_id());
		ps.setInt(4, item.getQuantity());
		
		return ps;
	}

	@Override
	public List<LineItem> getItemsByPOID(int po_id) {
		try (PreparedStatement ps = generateGetItemsByPOIDStatement(po_id);
			 ResultSet rs = ps.executeQuery()) 
		{
			ArrayList<LineItem> result = new ArrayList<>();
			while (rs.next()) {
				LineItem item = mapRSToLineItem(rs);
				result.add(item);
			}

			return result;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private LineItem mapRSToLineItem(ResultSet rs) throws SQLException{
		int po_id = rs.getInt(1);
		int line_no = rs.getInt(2);
		int prod_id = rs.getInt(3);
		int quantity = rs.getInt(4);
		
		return new LineItem(po_id, line_no, prod_id, quantity);
	}

	private PreparedStatement generateGetItemsByPOIDStatement(int po_id) 
			throws SQLException {
		String SQL = "SELECT * FROM line_item " + 
					 "WHERE po_id = ?";
		PreparedStatement ps = db.prepareStatement(SQL);
		ps.setInt(1, po_id);
		return ps;
	}

}
