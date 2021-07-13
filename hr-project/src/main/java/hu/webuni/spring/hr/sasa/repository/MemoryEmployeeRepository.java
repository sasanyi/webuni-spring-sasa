package hu.webuni.spring.hr.sasa.repository;

import hu.webuni.spring.hr.sasa.model.Employee;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MemoryEmployeeRepository implements EmployeeRepository{

    private Map<Long, Employee> employeeMap = new HashMap<>();

    {
        employeeMap.put(1L, new Employee(1L, "Teszt Alkalmazott", "Hr", 100000, LocalDateTime.now().minusYears(5)));
        employeeMap.put(2L, new Employee(2L, "Teszt Alkalmazott2", "Hr2", 100000, LocalDateTime.now().minusYears(5)));
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(this.employeeMap.values());
    }

    @Override
    public Employee save(Employee employee) {

         employeeMap.put(employee.getId(), employee);
         return employee;
    }

    @Override
    public void deleteById(Long id) {
        employeeMap.remove(id);
    }

    @Override
    public Employee findById(Long id) {
        return employeeMap.get(id);
    }

    @Override
    public List<Employee> findAllBySalaryGraterThan(int salary) {
        return this.employeeMap.values().stream().filter(employee -> employee.getSalary() > salary).collect(Collectors.toList());
    }
}
