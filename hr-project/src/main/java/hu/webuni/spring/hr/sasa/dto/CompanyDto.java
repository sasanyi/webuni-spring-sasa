package hu.webuni.spring.hr.sasa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hu.webuni.spring.hr.sasa.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private String id;
    private String name;
    private String address;

    public CompanyDto(Company company){
        this.id = company.getId();
        this.name = company.getName();
        this.address = company.getAddress();
    }

}
