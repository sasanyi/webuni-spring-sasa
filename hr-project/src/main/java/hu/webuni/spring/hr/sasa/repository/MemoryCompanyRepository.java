package hu.webuni.spring.hr.sasa.repository;

import hu.webuni.spring.hr.sasa.dto.EmployeeDto;
import hu.webuni.spring.hr.sasa.model.Company;
import hu.webuni.spring.hr.sasa.model.Employee;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryCompanyRepository implements CompanyRepository{

    private Map<String, Company> companyMap = new HashMap<>();

    {
        companyMap.put("AAAA", new Company("AAAA", "K&K Kft", "6000 Asd Kiss Geza utca 25", new ArrayList<>(){
            {

                add(new EmployeeDto(new Employee(1L, "Teszt Alkalmazott", "Hr", 100000, LocalDateTime.now().minusYears(5))));
            }
        }));
    }

    @Override
    public List<Company> findAll() {
        return new ArrayList<>(this.companyMap.values());
    }

    @Override
    public Company save(Company company) {

        companyMap.put(company.getId(), company);
        return company;
    }

    @Override
    public void deleteById(String id) {
        companyMap.remove(id);
    }

    @Override
    public Company findById(String id) {
        return companyMap.get(id);
    }


}
