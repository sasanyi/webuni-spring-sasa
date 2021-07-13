package hu.webuni.spring.hr.sasa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hu.webuni.spring.hr.sasa.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullCompanyDto extends CompanyDto{
    private List<EmployeeDto> employees;

    public FullCompanyDto(Company company){
        super(company);
        this.employees = company.getEmployees();
    }
}
