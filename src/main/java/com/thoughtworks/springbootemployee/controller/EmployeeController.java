package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.data.EmployeeData;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getEmployeesInformation(@RequestParam(name = "page" , required = false) Integer page,
                                 @RequestParam(name = "pageSize",required = false) Integer pageSize,
                                 @RequestParam(name =  "gender", required = false) String gender) {
        List<Employee> certainEmployees = employeeService.findAll();
        if(gender != null) {
            certainEmployees = certainEmployees.stream().filter(certainEmployee -> certainEmployee.getGender().equals(gender)).collect(Collectors.toList());
        }
        if(page != null && pageSize != null) {
            certainEmployees = certainEmployees.stream().skip(page - 1).limit(pageSize).collect(Collectors.toList());
        }
        return certainEmployees;
    }

    @GetMapping(path = "/{id}")
    public Employee getCertainEmployee(@PathVariable int id) {
        return employeeService.findEmployeeByID(id);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping(path = "/{employeeID}")
    public Employee updateEmployeeInformation(@RequestBody Employee employee, @PathVariable int employeeID) {
        return employeeService.update(employeeID, employee);
    }

    @DeleteMapping(path = "/{employeeID}")
    public Employee deleteEmployee(@PathVariable int employeeID) {
        return employeeService.deleteEmployee(employeeService.findEmployeeByID(employeeID));
    }
}
