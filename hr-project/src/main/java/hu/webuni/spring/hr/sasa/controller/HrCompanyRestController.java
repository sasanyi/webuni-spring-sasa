package hu.webuni.spring.hr.sasa.controller;

import hu.webuni.spring.hr.sasa.dto.AddUserToCompanyRequest;
import hu.webuni.spring.hr.sasa.dto.CompanyDto;
import hu.webuni.spring.hr.sasa.dto.EmployeeDto;
import hu.webuni.spring.hr.sasa.dto.FullCompanyDto;
import hu.webuni.spring.hr.sasa.model.Company;
import hu.webuni.spring.hr.sasa.model.Employee;
import hu.webuni.spring.hr.sasa.repository.CompanyRepository;
import hu.webuni.spring.hr.sasa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/companies")
public class HrCompanyRestController {
    private CompanyRepository companyRepository;
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<CompanyDto> listAll(@RequestParam(defaultValue = "false", required = false) Boolean full){
        if(full == null || !full){
            return this.companyRepository.findAll().stream().map(CompanyDto::new).collect(Collectors.toList());
        }
        return this.companyRepository.findAll().stream().map(FullCompanyDto::new).collect(Collectors.toList());


    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getById(@PathVariable String id,
                                              @RequestParam(defaultValue = "false", required = false) Boolean full){

        Company company =  this.companyRepository.findById(id);
        if(company == null)
            return ResponseEntity.notFound().build();
        if(full == null || !full){
            return ResponseEntity.ok(new CompanyDto(company));
        }
        return ResponseEntity.ok(new FullCompanyDto(company));
    }

    @PostMapping
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto){
        return new CompanyDto(this.companyRepository.save(new Company(companyDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> editCompany( @PathVariable String id, @RequestBody CompanyDto companyDto){
        if(this.companyRepository.findById(id) != null) {
            companyDto.setId(id);
            return ResponseEntity.ok(new CompanyDto(this.companyRepository.save(new Company(companyDto))));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/changeEmployeeList/{id}")
    public ResponseEntity<CompanyDto> editCompany( @PathVariable String id, @RequestBody List<Long> companyDto){
        Company company = this.companyRepository.findById(id);
        if(company != null) {
            List<EmployeeDto> employeeDtos = companyDto.stream().map(employeeId -> new EmployeeDto(this.employeeRepository.findById(employeeId))).collect(Collectors.toList());
            company.setEmployees(employeeDtos);
            return ResponseEntity.ok(new FullCompanyDto(this.companyRepository.save(company)));
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/addUserToCompany")
    public ResponseEntity<CompanyDto> addUserToCompany( @RequestBody AddUserToCompanyRequest request){
        Company company = this.companyRepository.findById(request.getCompanyId());
        if(company != null) {
            company.getEmployees().add(new EmployeeDto(this.employeeRepository.findById(request.getUserId())));

            return ResponseEntity.ok(new FullCompanyDto(this.companyRepository.save(company)));
        }
        return ResponseEntity.notFound().build();
    }



    @DeleteMapping("/deleteUserFromCompany")
    public ResponseEntity<CompanyDto> deleteUserFromCompany( @RequestBody AddUserToCompanyRequest request){
        Company company = this.companyRepository.findById(request.getCompanyId());
        if(company != null) {
            company.getEmployees().remove(new EmployeeDto(this.employeeRepository.findById(request.getUserId())));

            return ResponseEntity.ok(new FullCompanyDto(this.companyRepository.save(company)));
        }
        return ResponseEntity.notFound().build();
    }
    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
}
