package db;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if (conn == null) {
			try {
				//Carregar dados da propiedade
				Properties props = loadProperties();
				//Carrega a url da propiedade
				String url = props.getProperty("dburl");
				//Cria conexão com o banco de dados
				conn = DriverManager.getConnection(url, props);	
			}catch( SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void  closeConnection() {
		if (conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DbException(e.getMessage());
			}
		}
		
	}
		
	private static Properties loadProperties() {
		//Cria uma Stream para leitura
		try(FileInputStream fs = new FileInputStream ("db.properties")){
			//Cria um objeto do tipo Properties
			Properties props = new Properties();
			//Carrega a stream com as propiedades no opbjeto
			props.load(fs);
			return props;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new DbException(e.getMessage());
		}
		
	}
	
	public static void closeStatement(Statement st) {
		if ( st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DbException(e.getMessage());
			}
		}
		
	}
	
	public static void closeResultSet(ResultSet rs) {
		if ( rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DbException(e.getMessage());
			}
		}
		
	}
}
