package hu.webuni.spring.hr.sasa.service;

import hu.webuni.spring.hr.sasa.dto.EmployeeDto;
import hu.webuni.spring.hr.sasa.model.Company;
import hu.webuni.spring.hr.sasa.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public Company create(Company company){
        return this.companyRepository.save(company);
    }

    public Company findById(String companyId) {
        return this.companyRepository.findById(companyId);
    }

    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


}
