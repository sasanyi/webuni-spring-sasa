package hu.webuni.spring.hr.sasa.controller;

import com.fasterxml.jackson.annotation.JsonView;
import hu.webuni.spring.hr.sasa.dto.AddUserToCompanyRequest;
import hu.webuni.spring.hr.sasa.dto.CompanyDto;
import hu.webuni.spring.hr.sasa.dto.Views;
import hu.webuni.spring.hr.sasa.mapper.CompanyMapper;
import hu.webuni.spring.hr.sasa.mapper.EmployeeMapper;
import hu.webuni.spring.hr.sasa.model.Company;
import hu.webuni.spring.hr.sasa.model.Employee;
import hu.webuni.spring.hr.sasa.service.CompanyService;
import hu.webuni.spring.hr.sasa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/companies")
public class HrCompanyRestController {
    private CompanyService companyService;
    private EmployeeService employeeService;

    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping(params = "full=true")
    public List<CompanyDto> listAllWithEmployees(){
        return this.companyMapper.companiesToDtos(this.companyService.findAll());
    }

    @GetMapping
    @JsonView(Views.BaseView.class)
    public List<CompanyDto> listAll(){
        return this.companyMapper.companiesToDtos(this.companyService.findAll());
    }

    @GetMapping(value = "/{id}", params = "full=true")
    public ResponseEntity<CompanyDto> getById(@PathVariable String id){

        Company company =  this.companyService.findById(id);
        if(company == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(this.companyMapper.companyToDto(company));
    }

    @GetMapping(value = "/{id}")
    @JsonView(Views.BaseView.class)
    public ResponseEntity<CompanyDto> getByIdWithEmployees(@PathVariable String id){

        Company company =  this.companyService.findById(id);
        if(company == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(this.companyMapper.companyToDto(company));
    }

    @PostMapping
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto){
        return this.companyMapper.companyToDto(this.companyService.create(this.companyMapper.dtoToCompany(companyDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> editCompany( @PathVariable String id, @RequestBody CompanyDto companyDto){
        if(this.companyService.findById(id) != null) {
            companyDto.setId(id);
            return ResponseEntity.ok(this.companyMapper.companyToDto(this.companyService.create(this.companyMapper.dtoToCompany(companyDto))));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/changeEmployeeList/{id}")
    public ResponseEntity<CompanyDto> editCompany( @PathVariable String id, @RequestBody List<Long> employeeIds){
        Company company = this.companyService.findById(id);
        if(company != null) {
            List<Employee> employees = employeeIds.stream().map(employeeId -> this.employeeService.findById(employeeId)).collect(Collectors.toList());
            company.setEmployees(employees);
            return ResponseEntity.ok(this.companyMapper.companyToDto(this.companyService.create(company)));
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/addUserToCompany")
    public ResponseEntity<CompanyDto> addUserToCompany( @RequestBody AddUserToCompanyRequest request){
        Company company = this.companyService.findById(request.getCompanyId());
        if(company != null) {
            company.getEmployees().add(this.employeeService.findById(request.getUserId()));

            return ResponseEntity.ok(this.companyMapper.companyToDto(this.companyService.create(company)));
        }
        return ResponseEntity.notFound().build();
    }



    @DeleteMapping("/deleteUserFromCompany")
    public ResponseEntity<CompanyDto> deleteUserFromCompany( @RequestBody AddUserToCompanyRequest request){
        Company company = this.companyService.findById(request.getCompanyId());
        if(company != null) {
            company.getEmployees().remove(this.employeeService.findById(request.getUserId()));

            return ResponseEntity.ok(this.companyMapper.companyToDto(this.companyService.create(company)));
        }
        return ResponseEntity.notFound().build();
    }
    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
}
