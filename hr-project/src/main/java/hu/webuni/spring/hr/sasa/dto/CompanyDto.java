package hu.webuni.spring.hr.sasa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import hu.webuni.spring.hr.sasa.model.Company;
import hu.webuni.spring.hr.sasa.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    @JsonView(Views.BaseView.class)
    private String id;
    @JsonView(Views.BaseView.class)
    private String name;
    @JsonView(Views.BaseView.class)
    private String address;
    private List<Employee> employees;


}
