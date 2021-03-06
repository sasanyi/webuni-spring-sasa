package hu.webuni.spring.hr.sasa.model;

import hu.webuni.spring.hr.sasa.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;
    private String fullName;
    private String role;
    private Integer salary;
    private LocalDateTime startAt;

}
