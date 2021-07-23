package hu.webuni.spring.hr.sasa.controller;

import hu.webuni.spring.hr.sasa.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HrEmployeeRestControllerIT {

    @Autowired
    WebTestClient webTestClient;


    private static final String BASE_URI="/api/employees";

    @Test
    void testThatCreatedEmployeeIsListed(){
        List<EmployeeDto> employeesBeforeCreate = getAllEmployees();

        EmployeeDto newEmployee = new EmployeeDto(12L, "Teszt Elek", "CEO", 1000000, LocalDateTime.now().minusYears(10));
        createEmployee(newEmployee);

        List<EmployeeDto> employeesAfterCreate = getAllEmployees();

        assertThat(employeesAfterCreate.subList(0, employeesBeforeCreate.size()))
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(employeesBeforeCreate);

        assertThat(employeesAfterCreate.get(employeesAfterCreate.size()-1))
                .usingRecursiveComparison()
                .isEqualTo(newEmployee);
    }

    @Test
    void testThatCreatedEmployeeWithWrongDto(){
        EmployeeDto newEmployee = new EmployeeDto(12L, "Teszt Elek", "CEO", 1000000, LocalDateTime.now().plusYears(10));
        webTestClient
                .post()
                .uri(BASE_URI)
                .bodyValue(newEmployee)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    void testUpdateEmployee(){
        EmployeeDto updateEmployee = new EmployeeDto(12L, "Teszt Elek Gábor", "CEO", 1000000, LocalDateTime.now().minusYears(10));
        updateEmployee(updateEmployee, 12L);
        List<EmployeeDto> employeesAfterUpdate = getAllEmployees();
        EmployeeDto finded = employeesAfterUpdate.stream().filter(e-> e.getId() == 12L).findFirst().get();
        assertThat(finded).isNotNull();
        assertThat(finded.getFullName()).isEqualTo("Teszt Elek Gábor");

    }
    @Test
    void testUpdateEmployeeWithWrongId(){
        EmployeeDto updateEmployee = new EmployeeDto(12L, "Teszt Elek Gábor", "CEO", 1000000, LocalDateTime.now().minusYears(10));
        webTestClient
                .put()
                .uri(BASE_URI+"/"+200)
                .bodyValue(updateEmployee)
                .exchange()
                .expectStatus()
                .isNotFound();

    }

    private void updateEmployee(EmployeeDto employeeDto, Long id){
        webTestClient
                .put()
                .uri(BASE_URI+"/"+id)
                .bodyValue(employeeDto)
                .exchange()
                .expectStatus()
                .isOk();

    }

    private void createEmployee(EmployeeDto employeeDto) {
        webTestClient
                .post()
                .uri(BASE_URI)
                .bodyValue(employeeDto)
                .exchange()
                .expectStatus()
                .isOk();

    }

    private List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> responseList = webTestClient
                .get()
                .uri(BASE_URI)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(EmployeeDto.class)
                .returnResult().getResponseBody();


        assert responseList != null;
        responseList.sort(Comparator.comparingLong(EmployeeDto::getId));
        return responseList;
    }


}
