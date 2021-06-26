package hu.webuni.spring.hr.sasa.config;

import hu.webuni.spring.hr.sasa.service.DefaultEmployeeService;
import hu.webuni.spring.hr.sasa.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!smart")
public class DefaultConfig {
    @Bean
    public EmployeeService employeeService(){
        return new DefaultEmployeeService();
    }
}
