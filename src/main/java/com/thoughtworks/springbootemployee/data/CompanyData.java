package com.thoughtworks.springbootemployee.data;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class CompanyData {
    List<Company> companies = new ArrayList<>();

    public CompanyData() {
        companies.add(new Company(1, "OOCL", 0, new ArrayList<>()));
        companies.add(new Company(2, "OOCL", 0, new ArrayList<>()));
        companies.add(new Company(3, "OOCL", 0, new ArrayList<>()));
        companies.add(new Company(4, "OOCL", 0, new ArrayList<>()));
        companies.add(new Company(5, "OOCL", 0, new ArrayList<>()));
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public Boolean addCompany(Company company) {
        return this.companies.add(company);
    }
}
