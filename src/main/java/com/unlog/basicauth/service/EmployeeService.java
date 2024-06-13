package com.unlog.basicauth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.unlog.basicauth.model.Employee;
import com.unlog.basicauth.model.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository repo;

		public EmployeeService(EmployeeRepository repo) {
			super();
			this.repo = repo;
		}
		
		 public List<Employee> findAll() {
		        return repo.findAll();
		    }

		    public Optional<Employee> findById(Long id) {
		        return repo.findById(id);
		    }

		    public Employee save(Employee employee) {
		        return repo.save(employee);
		    }

		    public void deleteById(Long id) {
		        repo.deleteById(id);
		    }
		    
		    public Optional<Employee> updateEmployee(Long id, Employee emp){
		    	
		    	return repo.findById(id).map(
		    			employee -> {
		    				employee.setName(emp.getName());
		    				employee.setEmail(emp.getEmail());
		    				employee.setDepartment(emp.getDepartment());
		    				employee.setCompany(emp.getCompany());
		    				return Optional.of(repo.save(emp));
		    			}).orElse(Optional.empty());
		    }
}
