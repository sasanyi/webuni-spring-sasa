package hu.webuni.spring.hr.sasa.controller;

import hu.webuni.spring.hr.sasa.model.Employee;
import hu.webuni.spring.hr.sasa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/employeeService")
public class HrEmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public HrEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Map<String, Integer> get(@RequestBody Employee employee){
        Map<String, Integer> res = new HashMap<>();
        res.put("percent", this.employeeService.getPayRaisePercent(employee));
        return res;
    }
}
