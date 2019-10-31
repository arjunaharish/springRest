package com.shoppingcart.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.configuration.Employee;

public class EmployeeServImpl {
	 @Autowired
	    EmployeeDaoImpl empdao;
	public List<Employee> getAllEmployees() {
        return empdao.getAllEmployeesFromDb();
    }	
}
