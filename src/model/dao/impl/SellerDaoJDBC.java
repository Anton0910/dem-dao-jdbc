package model.dao.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import db.DbException;
import model.dao.SellerDao;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	public static Connection conn = null;
	
	public Connection getConnection() {
		if(conn == null) {
			try {
			FileInputStream fs = new FileInputStream("db.properties");	
			Properties props = new Properties();
			props.load(fs);
			String url = props.getProperty("dburl");
			conn = DriverManager.getConnection(url, props);
			return conn;
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}catch (IOException e) {
				// TODO Auto-generated catch block
				throw new DbException(e.getMessage());
			}
		}
		return conn;
		
		
	}

	@Override
	public void insert(Seller obj) {
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findALL() {
		// TODO Auto-generated method stub
		return null;
	}

}
