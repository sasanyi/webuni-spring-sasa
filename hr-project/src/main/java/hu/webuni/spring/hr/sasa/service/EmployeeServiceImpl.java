package hu.webuni.spring.hr.sasa.service;

import hu.webuni.spring.hr.sasa.model.Employee;
import hu.webuni.spring.hr.sasa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findById(long id) {
        return this.employeeRepository.findById(id);
    }

    @Override
    public Employee create(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public List<Employee> findAllBySalaryGraterThan(Integer salary) {
        return this.employeeRepository.findAllBySalaryGraterThan(salary);
    }
}
