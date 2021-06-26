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
        System.out.println("Eredeti: "+employee1.getSalary());
        this.salaryService.setNewSalary(employee1);
        System.out.println("Új: "+employee1.getSalary()); // EXPECTED 1050 OR 1000

        Employee employee2 = new Employee(0L, "Teszt Béla", "Somthing",1000 ,LocalDateTime.now().minusYears(10));
        System.out.println("Eredeti: "+employee2.getSalary());
        this.salaryService.setNewSalary(employee2);
        System.out.println("Új: "+employee2.getSalary()); // EXPECTED 1050 OR 1100

        Employee employee3 = new Employee(0L, "Teszt Béla", "Somthing",1000 ,LocalDateTime.now().minusYears(5));
        System.out.println("Eredeti: "+employee3.getSalary());
        this.salaryService.setNewSalary(employee3);
        System.out.println("Új: "+employee3.getSalary()); // EXPECTED 1050 OR 1050

        Employee employee4 = new Employee(0L, "Teszt Béla", "Somthing",1000 ,LocalDateTime.now().minusYears(2));
        System.out.println("Eredeti: "+employee4.getSalary());
        this.salaryService.setNewSalary(employee4);
        System.out.println("Új: "+employee4.getSalary()); // EXPECTED 1050 OR 1000

        Employee employee5 = new Employee(0L, "Teszt Béla", "Somthing",1000 ,LocalDateTime.now().minusYears(2).minusMonths(7));
        System.out.println("Eredeti: "+employee5.getSalary());
        this.salaryService.setNewSalary(employee5);
        System.out.println("Új: "+employee5.getSalary()); // EXPECTED 1050 OR 1020



    }
}
