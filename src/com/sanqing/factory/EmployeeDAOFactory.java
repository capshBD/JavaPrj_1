package com.sanqing.factory;

import com.sanqing.dao.EmployeeDAO;
import com.sanqing.daoImpl.EmployeeDAOImpl;

public class EmployeeDAOFactory {
	public static EmployeeDAO getEmployeeDAOInstance(){
		return new EmployeeDAOImpl();
	}
}
