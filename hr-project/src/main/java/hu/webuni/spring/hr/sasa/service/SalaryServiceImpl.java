package hu.webuni.spring.hr.sasa.service;

import hu.webuni.spring.hr.sasa.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl implements SalaryService{

    private EmployeeService employeeService;

    @Override
    public void setNewSalary(Employee employee) {
        int percent = this.employeeService.getPayRaisePercent(employee);
        double incrementPercent = (100+percent)/100.0;
        int actualSalary = employee.getSalary();

        employee.setSalary((int) (actualSalary*incrementPercent));

    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
