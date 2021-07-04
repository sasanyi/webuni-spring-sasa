package hu.webuni.spring.hr.sasa.controller;

import hu.webuni.spring.hr.sasa.model.Employee;
import hu.webuni.spring.hr.sasa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Map;

@Controller

public class HrWebController {

    private EmployeeRepository employeeRepository;

    @GetMapping({"/employees"})
    public String index(Map<String, Object> model){
        model.put("allEmployee", this.employeeRepository.findAll());
        model.put("newEmplyee", new Employee());
        return "index";
    }

    @GetMapping
    public String home(){
        return "redirect:employees";
    }

    @PostMapping({"/employees"})
    public String postEmployee(Employee employee){
        employee.setStartAt(LocalDateTime.now());
        this.employeeRepository.save(employee);
        return "redirect:employees";
    }





    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}
