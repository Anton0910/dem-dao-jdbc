package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department dp = new Department("Books", 1);
		
		Seller sl = new Seller(1,"ant","antonio@email",new Date(), 3000.00, dp );
		System.out.println(sl);

	}

}
