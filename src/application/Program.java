package application;

import java.util.Date;
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
		
		System.out.println("=== TEST 3: seller findAll ======");
		List<Seller> list2 = sd.findALL();
		for ( Seller obj : list2) {
			System.out.println(obj);
		}
		System.out.println("=== TEST 4: seller insert ======");
		Seller seller2 = new Seller(0, "Antonio","antonio@gmail",new Date(),3000.00,department);
		sd.insert(seller2);
		System.out.println("Inserted! New id = "+ seller2.getId());
		System.out.println("\n=== TEST 4: Update seller  ======\n");
		seller = sd.findById(24);
		seller.setName("Ana");
		sd.update(seller);
		

	}

}
