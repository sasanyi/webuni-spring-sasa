package hu.webuni.spring.hr.sasa.service;

import hu.webuni.spring.hr.sasa.model.Employee;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

@Service
public class SmartEmployeeService implements EmployeeService{

    @Override
    public int getPayRaisePercent(Employee employee) {
        long diffInMillies  = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()).getTime()
                - Date.from(employee.getStartAt().atZone(ZoneId.systemDefault()).toInstant()).getTime();
        double yearDiff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 365.0;

        if(yearDiff >= 10){
            return 10;
        }else if(yearDiff >= 5){
            return 5;
        }else if(yearDiff >= 2.5){
            return 2;
        }

        return 0;

    }
}
