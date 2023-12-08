package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	//No programa nao precisara instanciar um SellerDaojdbc, aepnas o DaoFactory, dando integridade ao sistema
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
}
