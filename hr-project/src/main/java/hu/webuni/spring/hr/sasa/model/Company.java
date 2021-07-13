package hu.webuni.spring.hr.sasa.model;

import hu.webuni.spring.hr.sasa.dto.CompanyDto;
import hu.webuni.spring.hr.sasa.dto.EmployeeDto;
import hu.webuni.spring.hr.sasa.dto.FullCompanyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private String id;
    private String name;
    private String address;
    private List<EmployeeDto> employees;

    public Company(FullCompanyDto companyDto){
        this.id = companyDto.getId();
        this.name = companyDto.getName();
        this.address = companyDto.getAddress();
        this.employees = companyDto.getEmployees();
    }

    public Company(CompanyDto companyDto){
        this.id = companyDto.getId();
        this.name = companyDto.getName();
        this.address = companyDto.getAddress();
    }
}
