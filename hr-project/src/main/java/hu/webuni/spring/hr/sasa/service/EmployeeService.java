package hu.webuni.spring.hr.sasa.service;

import hu.webuni.spring.hr.sasa.model.Employee;

import java.util.List;

public interface EmployeeService {
    int getPayRaisePercent(Employee employee);

    Employee findById(long id);

    Employee create(Employee employee);

    List<Employee> findAll();

    List<Employee> findAllBySalaryGraterThan(Integer salary);
}
