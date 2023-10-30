package edu.scranton.myretails.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.scranton.myretails.entity.Product;

public class ProductDAOImpl implements ProductDAO {
	private Connection db;

	public ProductDAOImpl(Connection db) {
		this.db = db;
	}

	@Override
	public Product insertProduct(Product product) {

		Product result = null;
		try (PreparedStatement ps = generateInsertProductStatement(product)) {
			ps.executeUpdate();
			product.setId(getMostRecentID());
			return product;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
	
	}

	@Override
	public List<Product> getAllProducts() {
		try (PreparedStatement ps = generateGetProductsStatement();
			 ResultSet rs = ps.executeQuery()) 
		{
			ArrayList<Product> result = new ArrayList<>();
			while (rs.next()) {
				Product product = mapRSToProduct(rs);
				result.add(product);
			}

			return result;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	
	}
	

	private PreparedStatement generateInsertProductStatement(Product product) 
			throws SQLException{

		String sql="INSERT INTO product(name, unit_cost)"
				 + "VALUES(?, ?)";
		PreparedStatement ps = db.prepareStatement(sql);
		ps.setString(1, product.getName());
		ps.setDouble(2, product.getUnit_cost());
		
		return ps;
	
	}
	
	private PreparedStatement generateGetProductsStatement() 
			throws SQLException{
		String SQL = "SELECT id, name, unit_cost "
				   + "FROM product ";
		return db.prepareStatement(SQL);
	
	}
	
	private Product mapRSToProduct(ResultSet rs) throws SQLException{
		int id=rs.getInt(1);
		String name=rs.getString(2);
		double unit_cost=rs.getDouble(3);
		return new Product(id, name, unit_cost);
	
	}
	
	private int getMostRecentID() {
		String SQL="SELECT max(id) FROM product";
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
