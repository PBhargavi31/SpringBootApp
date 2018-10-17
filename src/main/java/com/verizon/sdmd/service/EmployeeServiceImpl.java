package com.verizon.sdmd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.sdmd.dao.EmpMongoRepository;
import com.verizon.sdmd.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmpMongoRepository empDao;

	@Override
	public void addEmployee(Employee emp) {
		empDao.insert(emp);

	}

	@Override
	public void removeEmployee(int empId) {
		empDao.deleteById(empId);

	}

	@Override
	public void updateEmployee(Employee emp) {
		empDao.save(emp);

	}

	@Override
	public Employee getEmployee(int empId) {
		Optional<Employee> opt = empDao.findById(empId);
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return empDao.findAll();
	}

	
	@Override
	public boolean exists(int empId) {
		return empDao.existsById(empId);
	}

	@Override
	public boolean existsByDept(String dept) {
		return empDao.findByDept(dept);
	}
}
