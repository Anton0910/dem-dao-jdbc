package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
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
		PreparedStatement st = null;
		ResultSet rs = null;
		
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			//Se o proximo rs for nullo significa que nao há sellers para consulta
			if(rs.next()) {
				Department dp = instantiateDepartment(rs);
				Seller sl = instantiateSeller(rs, dp);
				return sl;
			}
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
		return null;
	}
	
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
		}
	
	
		private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
		}


	@Override
	public List<Seller> findALL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> FindByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE Department.Id = ? "
					+ "ORDER BY Name");
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();
			//Usa-se o while pois pode retornar mais de um valor
			while(rs.next()) {
				//Map nao admite repetição, se o Departamento nao existir, ira atribuir nulo, caso contrario ira atribuir retornar um valor para indicar que ja existe
				Department dp = map.get(rs.getInt("DepartmentId"));
				if ( dp == null) {
					dp = instantiateDepartment(rs);
					//adiciona o departamendo e o id para fazer a verificação antes
					map.put(rs.getInt("DepartmentId"), dp);
				}
				Seller sl = instantiateSeller(rs, dp);
				list.add(sl);
			}
			return list;	
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
	}

}
