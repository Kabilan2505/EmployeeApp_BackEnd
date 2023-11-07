package com.msp.Employee.Application.Controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msp.Employee.Application.Entity.Employees;
import com.msp.Employee.Application.Exception.ResourceNotFoundException;
import com.msp.Employee.Application.Repository.EmployeeRepository;


@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	
	@Autowired
	private EmployeeRepository repo;
	
	@GetMapping("/employees")
	public List<Employees> getAllEmployees(){
		return repo.findAll();
	}
	
	@PostMapping("/employees")
	public Employees createEmployee(@RequestBody Employees employee){
		return repo.save(employee);
		
	}
	
	@GetMapping("/employees/{id}")
	public Employees getEmployeeById(@PathVariable Long id){
		 Employees employee=repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :"+id));
		
		return employee;
	}
	
	@PutMapping("/employees/{id}")
	public Employees updateEmployee(@PathVariable Long id, @RequestBody Employees empdetails){
		Employees employee=repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :"+id));
		employee.setId(id);
	
		employee.setName(empdetails.getName());
		employee.setEmail(empdetails.getEmail());
		employee.setDept(empdetails.getDept());
		
		Employees updatedEmp=repo.save(employee);
		return updatedEmp;
		
	}
	
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable Long id){
		Employees employee=repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :"+id));
		
		
		
		 repo.delete(employee);
		 
		
	}

}
