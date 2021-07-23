package hu.webuni.spring.hr.sasa.controller;

import hu.webuni.spring.hr.sasa.model.Employee;
import hu.webuni.spring.hr.sasa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/employees/{id}")
    public String editEmployeeForm(@PathVariable long id, Map<String, Object> model){
        Employee get = this.employeeRepository.findById(id);
        if(get == null){
            return "redirect:/employees";
        }

        model.put("employee", get);
        return "edit-employee";
    }

    @PostMapping("/employees/{id}")
    public String editEmployee(Employee e){
        System.out.println("IDDDD" + e.getId());
        this.employeeRepository.save(e);
        return "redirect:/employees";
    }

    @GetMapping
    public String home(){
        return "redirect:employees";
    }

    @PostMapping({"/employees"})
    public String postEmployee(Employee employee){
        this.employeeRepository.save(employee);
        return "redirect:/employees";
    }

    @GetMapping({"/employees/delete/{id}"})
    public String deleteEmployee(@PathVariable("id") Long userId){
        this.employeeRepository.deleteById(userId);
        return "redirect:/employees";
    }





    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}
