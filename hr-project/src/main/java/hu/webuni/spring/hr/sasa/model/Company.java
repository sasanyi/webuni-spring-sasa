package hu.webuni.spring.hr.sasa.model;

import com.fasterxml.jackson.annotation.JsonView;
import hu.webuni.spring.hr.sasa.dto.Views;
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
    private List<Employee> employees;

}
