package hu.webuni.spring.hr.sasa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
