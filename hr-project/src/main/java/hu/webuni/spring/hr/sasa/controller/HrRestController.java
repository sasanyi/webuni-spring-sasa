package hu.webuni.spring.hr.sasa.controller;

import hu.webuni.spring.hr.sasa.dto.EmployeeDto;
import hu.webuni.spring.hr.sasa.model.Employee;
import hu.webuni.spring.hr.sasa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class HrRestController {
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<EmployeeDto> listAll(@RequestParam(required = false) Integer salary){
        if(salary != null){
            return this.employeeRepository.findAllBySalaryGraterThan(salary).stream().map(EmployeeDto::new).collect(Collectors.toList());
        }
        return this.employeeRepository.findAll().stream().map(EmployeeDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable long id){
        Employee employee =  this.employeeRepository.findById(id);
        if(employee == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new EmployeeDto(employee));
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto){
        return new EmployeeDto(this.employeeRepository.save(new Employee(employeeDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> createEmployee( @PathVariable long id, @RequestBody EmployeeDto employeeDto){
        if(this.employeeRepository.findById(id) != null) {
            employeeDto.setId(id);
            return ResponseEntity.ok(new EmployeeDto(this.employeeRepository.save(new Employee(employeeDto))));
        }
        return ResponseEntity.notFound().build();
    }




    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}
