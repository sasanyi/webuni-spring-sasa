package hu.webuni.spring.hr.sasa.service;

import hu.webuni.spring.hr.sasa.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmployeeService extends EmployeeServiceImpl{
    @Override
    public int getPayRaisePercent(Employee employee) {
        return 5;
    }
}
