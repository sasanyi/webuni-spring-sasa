package hu.webuni.spring.hr.sasa.mapper;

import hu.webuni.spring.hr.sasa.dto.EmployeeDto;
import hu.webuni.spring.hr.sasa.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    List<EmployeeDto> employeesToDtos(List<Employee> employees);
    EmployeeDto employeeToDto(Employee employee);
    Employee dtoToEmployee(EmployeeDto employeeDto);
}
