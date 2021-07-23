package hu.webuni.spring.hr.sasa.controller;

import hu.webuni.spring.hr.sasa.dto.EmployeeDto;
import hu.webuni.spring.hr.sasa.mapper.EmployeeMapper;
import hu.webuni.spring.hr.sasa.model.Employee;
import hu.webuni.spring.hr.sasa.repository.EmployeeRepository;
import hu.webuni.spring.hr.sasa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class HrEmployeeRestController {
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping
    public List<EmployeeDto> listAll(@RequestParam(required = false) Integer salary){
        if(salary != null){
            return this.employeeMapper.employeesToDtos(this.employeeService.findAllBySalaryGraterThan(salary));
        }
        return this.employeeMapper.employeesToDtos(this.employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable long id){
        Employee employee =  this.employeeService.findById(id);
        if(employee == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(this.employeeMapper.employeeToDto(employee));
    }

    @PostMapping
    public EmployeeDto createEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        return this.employeeMapper.employeeToDto(this.employeeService.create(this.employeeMapper.dtoToEmployee(employeeDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> createEmployee( @PathVariable long id, @Valid @RequestBody EmployeeDto employeeDto){
        if(this.employeeService.findById(id) != null) {
            employeeDto.setId(id);
            return ResponseEntity.ok(this.employeeMapper.employeeToDto(this.employeeService.create(this.employeeMapper.dtoToEmployee(employeeDto))));
        }
        return ResponseEntity.notFound().build();
    }




    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
