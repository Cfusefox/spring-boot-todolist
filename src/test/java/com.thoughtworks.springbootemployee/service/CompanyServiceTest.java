package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

public class CompanyServiceTest {

    @Test
    void should_return_company_list_when_find_company_information_given_null() {
        //given
        CompanyRepository mockedCompanyRepository = mock(CompanyRepository.class);
        CompanyService CompanyService = new CompanyService(mockedCompanyRepository);
        List<Company> companies = Arrays.asList(
                new Company(1, "alibaba", 50, null),
                new Company(2, "alibaba", 50, null)
        );
        given(mockedCompanyRepository.findAll()).willReturn(companies);
        //when
        List<Company> companyList = CompanyService.findAll();
        //then
        assertEquals(companies, companyList);
    }

    @Test
    void should_return_certain_company_when_find_company_by_id_given_company_id() {
        //given
        CompanyRepository mockedCompanyRepository = mock(CompanyRepository.class);
        CompanyService CompanyService = new CompanyService(mockedCompanyRepository);
        Company company = new Company(1, "alibaba", 50, null);
        given(mockedCompanyRepository.findById(1)).willReturn(java.util.Optional.of(company));
        //when
        Company certainCompany = CompanyService.findCompanyByID(1);
        //then
        assertEquals(company, certainCompany);
    }

    @Test
    void should_return_company_employee_list_when_find_company_employee_list_by_id_given_company_id() {
        //given
        CompanyRepository mockedCompanyRepository = mock(CompanyRepository.class);
        CompanyService CompanyService = new CompanyService(mockedCompanyRepository);
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Zach", 18, "male", 1000),
                new Employee(2, "York", 18, "male", 1000),
                new Employee(3, "Karen", 18, "female", 1000)
        );

        given(mockedCompanyRepository.findById(2)).willReturn(java.util.Optional.of(new Company(2, "OOCL", 0, employees)));
        //when
        List<Employee> employeeList = CompanyService.findCompanyEmployeesByID(2);
        //then

        assertEquals(employees, employeeList);
    }

    @Test
    void should_return_range_of_company_when_find_range_of_company_given_page_and_page_size() {
        //given
        CompanyRepository mockedCompanyRepository = mock(CompanyRepository.class);
        CompanyService employeeService = new CompanyService(mockedCompanyRepository);

        given(mockedCompanyRepository.findAll(PageRequest.of(3, 1))).willReturn(Page.empty());

        //when
        Page<Company> companyList = employeeService.findRangeOfCompany(3, 1);

        //then
        assertEquals(Page.empty(), companyList);
    }

    @Test
    void should_return_company_when_add_company_given_company() {
        //given
        CompanyRepository mockedCompanyRepository = mock(CompanyRepository.class);
        CompanyService CompanyService = new CompanyService(mockedCompanyRepository);
        Company company = new Company(1, "alibaba", 50, null);
        given(mockedCompanyRepository.save(company)).willReturn(company);
        //when
        Company addCompany = CompanyService.addCompany(company);
        //then

        assertEquals(company, addCompany);
    }

    @Test
    void should_return_company_when_delete_company_given_company_id() {
        //given
        CompanyRepository mockedCompanyRepository = mock(CompanyRepository.class);
        CompanyService CompanyService = new CompanyService(mockedCompanyRepository);
        given(mockedCompanyRepository.findById(1)).willReturn(java.util.Optional.of(new Company(1, "alibaba", 50, null)));
        //when
        Company company = CompanyService.deleteCompany(1);
        //then

        Mockito.verify(mockedCompanyRepository).deleteById(1);
    }

    @Test
    void should_return_update_company_when_update_company_given_company() {
        //given
        CompanyRepository mockedCompanyRepository = mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(mockedCompanyRepository);
        Company company = new Company(1, "OOCL", 0, new ArrayList<>());
        given(mockedCompanyRepository.findById(1)).willReturn(java.util.Optional.of(company));

        //when
        Company updateCompany = companyService.update(1, new Company(1, "OOIL", 0, new ArrayList<>()));


        //then
        assertEquals(company, updateCompany);
    }
}
