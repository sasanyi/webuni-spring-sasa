package hu.webuni.spring.hr.sasa.repository;

import hu.webuni.spring.hr.sasa.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll();
    Employee save(Employee employee);
    void deleteById(Long id);
    Employee findById(Long id);

    List<Employee> findAllBySalaryGraterThan(int salary);
}
