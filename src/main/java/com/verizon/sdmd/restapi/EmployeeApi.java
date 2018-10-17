package com.verizon.sdmd.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.sdmd.model.Employee;
import com.verizon.sdmd.service.EmployeeService;
@RestController
@CrossOrigin
@RequestMapping("/employees")
public class EmployeeApi {
    
    @Autowired
    private EmployeeService service;
    
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        ResponseEntity<List<Employee>> resp = null;
        List<Employee> employees = service.getAllEmployees();
        if (employees != null && employees.size() > 0)
            resp = new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
        else
            resp = new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        return resp;
    }
    
    @GetMapping("/{empid}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("empid") int empId) {
        ResponseEntity<Employee> resp = null;
        Employee employee = service.getEmployee(empId);
        if (employee != null)
            resp = new ResponseEntity<Employee>(employee, HttpStatus.OK);
        else
            resp = new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        return resp;
    }
    
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        ResponseEntity<Employee> resp = null;
        if (employee != null && !service.exists(employee.getEmpId())) {
            service.addEmployee(employee);
            resp = new ResponseEntity<Employee>(employee, HttpStatus.OK);
        } else
            resp = new ResponseEntity<Employee>(HttpStatus.CONFLICT);
        return resp;
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        ResponseEntity<Employee> resp = null;
        if (employee != null && service.exists(employee.getEmpId())) {
            service.updateEmployee(employee);
            resp = new ResponseEntity<Employee>(employee, HttpStatus.OK);
        } else
            resp = new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        return resp;
    }

    @DeleteMapping("/{empid}")
    public ResponseEntity<Void> removeEmployee(@PathVariable("empid") int empid) {
        ResponseEntity<Void> resp = null;
        if (service.exists(empid)) {
            service.removeEmployee(empid);
            resp = new ResponseEntity<>(HttpStatus.OK);
        } else
            resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return resp;
    }

}
