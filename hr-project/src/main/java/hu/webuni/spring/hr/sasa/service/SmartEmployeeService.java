package hu.webuni.spring.hr.sasa.service;

import hu.webuni.spring.hr.sasa.config.HrConfigProperties;
import hu.webuni.spring.hr.sasa.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class SmartEmployeeService implements EmployeeService{

    @Autowired
    private HrConfigProperties hrConfigProperties;

    @Override
    public int getPayRaisePercent(Employee employee) {
        long diffInMillies  = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()).getTime()
                - Date.from(employee.getStartAt().atZone(ZoneId.systemDefault()).toInstant()).getTime();
        double yearDiff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 365.0;

        int percent = 0;

        /*
        * Igazából több zavaró dolog is lehet ebben a megvalósításban:
        *   Mivel az év lehet valós szám is, így a properties fájl olvasásakor megzavaró lehet hr.percent.years.2.5=2 (ez orvosolható key-value pár felcserélésével)
        *   A másik egy hiba lehetőség, hogy ha az évek nem csökkenő sorrendben vannk a properties fájlban akkor nem működik megfelelően
        *       (ez orvosolható sortal a setterben)
        * */
        for(Map.Entry<Double, Integer> entry : hrConfigProperties.getPercent().getYears().entrySet()) {

            if(yearDiff >= entry.getKey()){

                percent = entry.getValue();
                break;
            }

        }


        return percent;

    }
}
