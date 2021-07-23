package hu.webuni.spring.hr.sasa.mapper;

import hu.webuni.spring.hr.sasa.dto.CompanyDto;
import hu.webuni.spring.hr.sasa.model.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    List<CompanyDto> companiesToDtos(List<Company> companies);


    CompanyDto companyToDto(Company company);


    Company dtoToCompany(CompanyDto companyDto);

}
