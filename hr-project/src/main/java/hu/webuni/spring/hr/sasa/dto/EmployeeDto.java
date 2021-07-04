package hu.webuni.spring.hr.sasa.dto;

import hu.webuni.spring.hr.sasa.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String fullName;
    private String role;
    private Integer salary;
    private LocalDateTime startAt;

    public EmployeeDto(Employee employee){
        this.id = employee.getId();
        this.fullName = employee.getFullName();
        this.role = employee.getRole();
        this.salary = employee.getSalary();
        this.startAt = employee.getStartAt();
    }
}
