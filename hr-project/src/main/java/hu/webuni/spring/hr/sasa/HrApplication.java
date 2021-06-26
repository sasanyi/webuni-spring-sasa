package hu.webuni.spring.hr.sasa;

import hu.webuni.spring.hr.sasa.model.Employee;
import hu.webuni.spring.hr.sasa.service.EmployeeService;
import hu.webuni.spring.hr.sasa.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

    @Autowired
    private SalaryService salaryService;

    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Employee employee1 = new Employee(0L, "Teszt Béla", "Somthing",1000 ,LocalDateTime.now());
        System.out.println(employee1.getSalary());
        this.salaryService.setNewSalary(employee1);
        System.out.println(employee1.getSalary());
    }
}
