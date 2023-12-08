package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sd = DaoFactory.createSellerDao();
		System.out.println("=== TEST 1: seller findById ======");
		Seller seller = sd.findById(23);
		System.out.println(seller);
		System.out.println("=== TEST 2: seller findByDepartement ======");
		Department department = new Department(null, 2);
		List<Seller> list = sd.FindByDepartment(department);
		for ( Seller obj : list) {
			System.out.println(obj);
		}
		
		

	}

}
