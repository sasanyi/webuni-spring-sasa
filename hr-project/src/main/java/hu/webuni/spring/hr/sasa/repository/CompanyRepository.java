package hu.webuni.spring.hr.sasa.repository;

import hu.webuni.spring.hr.sasa.model.Company;
import hu.webuni.spring.hr.sasa.model.Employee;

import java.util.List;

public interface CompanyRepository {
    List<Company> findAll();
    Company save(Company company);
    void deleteById(String id);
    Company findById(String id);
}
