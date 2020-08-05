package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    public Employee update(int employeeId, Employee newEmployee) {
        Employee updateEmployee = employeeRepository.findById(employeeId).orElse(null);
        updateEmployee.setAge(newEmployee.getAge());
        updateEmployee.setGender(newEmployee.getGender());
        updateEmployee.setName(newEmployee.getName());
        employeeRepository.save(updateEmployee);
        return updateEmployee;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeByID(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public List<Employee> findEmployeeByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public Page<Employee> getRangeOfEmployees(int page, int pageSize) {
        return this.employeeRepository.findAll(PageRequest.of(page, pageSize));
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee deleteEmployee(Employee employee) {
        this.employeeRepository.deleteById(employee.getEmployeeID());
        //todo logic
        return employee;
    }
}
