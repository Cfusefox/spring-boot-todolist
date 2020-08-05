package com.thoughtworks.springbootemployee.data;

import com.thoughtworks.springbootemployee.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
    List<Employee> employees = new ArrayList<>();

    public EmployeeData() {
        this.employees.add(new Employee(1, "Zach", 18, "male", 1000));
        this.employees.add(new Employee(2, "York", 18, "male", 1000));
        this.employees.add(new Employee(3, "Karen", 18, "female", 1000));
        this.employees.add(new Employee(4, "Alex", 18, "male", 1000));
        this.employees.add(new Employee(5, "Green", 18, "male", 1000));
    }

    public boolean addEmployee(Employee employee) {
        return this.employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
