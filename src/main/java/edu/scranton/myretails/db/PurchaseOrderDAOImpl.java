package edu.scranton.myretails.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import edu.scranton.myretails.entity.PurchaseOrder;

public class PurchaseOrderDAOImpl implements PurchaseOrderDAO {
	
	private Connection db;
	
	public PurchaseOrderDAOImpl(Connection db) {
		this.db = db;
	}

	@Override
	public PurchaseOrder insertPurchaseOrder(PurchaseOrder order) {
		PurchaseOrder result = null;
		try (PreparedStatement ps = generateInsertPurchaseOrderStatement(order)) {
			ps.executeUpdate();
			
			order.setId(getMostRecentID());
			return order;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
	}
	
	private PreparedStatement generateInsertPurchaseOrderStatement(PurchaseOrder order)
			throws SQLException {
		String sql="INSERT INTO p_order(cust_id, o_date, total_cost)"
				 + "VALUES(?, ?, ?)";
		PreparedStatement ps = db.prepareStatement(sql);
		ps.setInt(1, order.getCust_id());
		
		String date=order.getO_date();
		ps.setDate(2, Date.valueOf(date)); 
		
		ps.setDouble(3, order.getTotal_cost());
		
		return ps;
	}

	private int getMostRecentID() {
		String SQL="SELECT max(id) FROM p_order";
		try(PreparedStatement ps=db.prepareStatement(SQL);
				ResultSet rs=ps.executeQuery()){
			rs.next();
			return rs.getInt(1);
		}
		catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public void updateTotalCost(int po_id, double total_cost) {
		try (PreparedStatement ps = generateUpdateCostStatement(po_id, total_cost)) {
			ps.executeUpdate();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private PreparedStatement generateUpdateCostStatement(int po_id, double total_cost) 
			throws SQLException {
		String SQL = "UPDATE p_order "+
					 "SET total_cost = ?" +
					 "WHERE id = ?";
		PreparedStatement ps = db.prepareStatement(SQL);
		ps.setDouble(1, total_cost);
		ps.setInt(2, po_id);
		return ps;
	}

	@Override
	public List<PurchaseOrder> getOrdersByCustomerID(int cust_id) {
		try (PreparedStatement ps = generateGetOrdersByCustomerIDStatement(cust_id);
			 ResultSet rs = ps.executeQuery()) 
		{
			ArrayList<PurchaseOrder> result = new ArrayList<>();
			while (rs.next()) {
				PurchaseOrder order = mapRSToPurchaseOrder(rs);
				result.add(order);
			}

			return result;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private PurchaseOrder mapRSToPurchaseOrder(ResultSet rs) throws SQLException {
		int id = rs.getInt(1);
		int cust_id = rs.getInt(2);
		String o_date = rs.getString(3);
		double total_cost = rs.getDouble(4);
		return new PurchaseOrder(id, cust_id, o_date, total_cost);
	}

	private PreparedStatement generateGetOrdersByCustomerIDStatement(int cust_id)
			throws SQLException {
		String SQL = "SELECT * FROM p_order " + 
					 "WHERE cust_id = ?";
		PreparedStatement ps = db.prepareStatement(SQL);
		
		ps.setInt(1, cust_id);
		return ps;
	}

	@Override
	public PurchaseOrder getOrderByPOID(int po_id) {
		try (PreparedStatement ps = generateGetOrderByPOID(po_id);
				 ResultSet rs = ps.executeQuery()) 
			{
				PurchaseOrder result = null;
				if (rs.next()) {
					result = mapRSToPurchaseOrder(rs);
				}

				return result;
			}
			catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
	}

	private PreparedStatement generateGetOrderByPOID(int po_id) 
			throws SQLException {
		String SQL = "SELECT * FROM p_order " +
					 "WHERE id = ?";
		PreparedStatement ps = db.prepareStatement(SQL);
		ps.setInt(1, po_id);
		return ps;
	}

	@Override
	public List<PurchaseOrder> getAllOrders() {
		try (PreparedStatement ps = generateGetOrdersStatement();
				 ResultSet rs = ps.executeQuery()) 
			{
				ArrayList<PurchaseOrder> result = new ArrayList<>();
				while (rs.next()) {
					PurchaseOrder order = mapRSToPurchaseOrder(rs);
					result.add(order);
				}

				return result;
			}
			catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
	}

	private PreparedStatement generateGetOrdersStatement()
			throws SQLException {
		String SQL = "SELECT * FROM p_order";
		return db.prepareStatement(SQL);
	}

}
