package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class EmployeeServiceTest {

    @Test
    void should_update_employee_when_update_employee_by_id_given_employee_information() {
        //given
        EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);
        Employee employee = new Employee(3, "York", 18, "male", 1000);
        given(mockedEmployeeRepository.findById(5)).willReturn(java.util.Optional.of(employee));

        //when
        Employee updateEmployee = employeeService.update(5, new Employee(2, "York", 18, "male", 1000));


        //then
        assertEquals(employee, updateEmployee);

    }

    @Test
    void should_all_employee_information_when_find_all_employee_information_given_null() {
        //given
        EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(3, "York", 18, "male", 1000));
        employeeList.add(new Employee(5, "York", 18, "male", 1000));
        employeeList.add(new Employee(4, "York", 18, "male", 1000));
        given(mockedEmployeeRepository.findAll()).willReturn(employeeList);

        //when
        List<Employee> allEmployee = employeeService.findAll();

        //then

        assertEquals(employeeList, allEmployee);
    }

    @Test
    void should_certain_employee_when_find_employee_by_id_given_id() {
        //given
        EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);
        Employee employee = new Employee(3, "York", 18, "male", 1000);
        given(mockedEmployeeRepository.findById(3)).willReturn(java.util.Optional.of(employee));

        //when
        Employee certainEmployee = employeeService.findEmployeeByID(3);

        //then
        assertEquals(employee, certainEmployee);
    }

    @Test
    void should_certain_gender_employee_when_find_employee_by_gender_given_gender() {
        //given
        EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(3, "York", 18, "male", 1000));
        employeeList.add(new Employee(5, "York", 18, "male", 1000));
        given(mockedEmployeeRepository.findAllByGender("male")).willReturn(employeeList);
        //when
        List<Employee> employees = employeeService.findEmployeeByGender("male");
        //then
        assertEquals(employeeList, employees);

    }

    @Test
    void should_range_of_employee_when_get_range_of_employees_given_page_and_page_size() {
        //given
        EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);
        given(mockedEmployeeRepository.findAll(PageRequest.of(3, 3))).willReturn(Page.empty());

        //when
        Page<Employee> employees = employeeService.getRangeOfEmployees(3, 3);

        //then
        assertEquals(Page.empty(), employees);
    }

    @Test
    void should_return_employee_when_add_employee_given_employee() {
        //given
        EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);
        Employee employee = new Employee(3, "York", 18, "male", 1000);

        given(mockedEmployeeRepository.save(employee)).willReturn(employee);

        //when
        Employee addedEmployee = employeeService.addEmployee(employee);

        //then
        assertEquals(employee, addedEmployee);
    }

    @Test
    void should_return_employee_when_delete_employee_given_employee() {
        //given
        EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);
        Employee employee = new Employee(3, "York", 18, "male", 1000);
        given(mockedEmployeeRepository.findById(employee.getEmployeeID())).willReturn(java.util.Optional.of(employee));

        //when
        Employee deletedEmployee = employeeService.deleteEmployee(employee);

        //then
        assertEquals(employee, deletedEmployee);
    }
}
