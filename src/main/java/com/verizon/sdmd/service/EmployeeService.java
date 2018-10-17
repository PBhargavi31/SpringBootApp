package com.verizon.sdmd.service;

import java.util.List;

import com.verizon.sdmd.model.Employee;

public interface EmployeeService {
	void addEmployee(Employee emp);

	void removeEmployee(int empId);

	void updateEmployee(Employee emp);

	Employee getEmployee(int empId);

	List<Employee> getAllEmployees();
	boolean existsByDept(String dept);


	public boolean exists(int empId);

}
