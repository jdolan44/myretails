package edu.scranton.myretails.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.scranton.myretails.entity.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	private Connection db;

	public CustomerDAOImpl(Connection db) {
		this.db = db;
	}

	@Override
	public Customer insertCustomer(Customer customer) {
		Customer result = null;
		try (PreparedStatement ps = generateInsertCustomerStatement(customer)) {
			ps.executeUpdate();
			
			customer.setId(getMostRecentID());
			return customer;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
	}

	@Override
	public Customer getCustomerById(int id) {
		try (PreparedStatement ps = generateGetCustomerByIdStatement(id);
				 ResultSet rs = ps.executeQuery()) {
				Customer customer = null;
				if(rs.next()) {
					customer = mapRSToCustomer(rs);
				}
				return customer;
			}
			catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
	}

	@Override
	public List<Customer> getAllCustomers() {
		
		try (PreparedStatement ps = generateGetCustomersStatement();
			 ResultSet rs = ps.executeQuery()) 
		{
			ArrayList<Customer> result = new ArrayList<>();
			while (rs.next()) {
				Customer customer = mapRSToCustomer(rs);
				result.add(customer);
			}

			return result;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}

	private PreparedStatement generateInsertCustomerStatement(Customer customer) 
			throws SQLException {
		String sql="INSERT INTO customer(name, email)"
				 + "VALUES(?, ?)";
		PreparedStatement ps = db.prepareStatement(sql);
		ps.setString(1, customer.getName());
		ps.setString(2, customer.getEmail());
		
		return ps;
	}
	
	private PreparedStatement generateGetCustomersStatement()
			throws SQLException{
		String SQL = "SELECT id, name, email "
				   + "FROM customer ";
		return db.prepareStatement(SQL);
	}
	
	private PreparedStatement generateGetCustomerByIdStatement(int id)
			throws SQLException{
		String SQL = "SELECT id, name, email "
				   + "FROM customer "
				   + "WHERE id = ?";
		PreparedStatement ps = db.prepareStatement(SQL);
		ps.setInt(1, id);
		
		return ps;
	}
	
	private Customer mapRSToCustomer(ResultSet rs) throws SQLException{
		int id=rs.getInt(1);
		String name=rs.getString(2);
		String email=rs.getString(3);
		return new Customer(id, name, email);
	}
	
	private int getMostRecentID() {
		String SQL="SELECT max(id) FROM customer";
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

}
