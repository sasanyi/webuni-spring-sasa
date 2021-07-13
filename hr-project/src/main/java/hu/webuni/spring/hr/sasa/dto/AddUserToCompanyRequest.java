package hu.webuni.spring.hr.sasa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserToCompanyRequest {
    private Long userId;
    private String companyId;
}
