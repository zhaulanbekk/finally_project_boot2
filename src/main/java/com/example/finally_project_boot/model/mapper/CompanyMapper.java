package com.example.finally_project_boot.model.mapper;

import com.example.finally_project_boot.dto.request.CompanyRequest;
import com.example.finally_project_boot.dto.response.CompanyResponse;
import com.example.finally_project_boot.model.entity.Company;
import com.example.finally_project_boot.repository.CompanyRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyMapper {
    private final CompanyRepository companyRepository;

    public CompanyMapper(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company mapToEntity(Long id, CompanyRequest companyRequest) {
        if (companyRequest == null) {
            return null;
        }
        Company company = new Company();
        company.setId(id);
        company.setCompanyName(companyRequest.getCompanyName());
        company.setLocatedCountry(companyRequest.getLocatedCountry());

        return company;
    }

    public List<CompanyResponse> mapToResponse(List<Company> companies) {
        List<CompanyResponse> responses = new ArrayList<>();
        for (Company company : companies) {
            responses.add(mapToResponse(company));
        }
        return responses;
    }

    public CompanyResponse mapToResponse(Company company) {
        if (company == null) {
            return null;
        }
        CompanyResponse companyResponse= new CompanyResponse();
        if (company.getId() != null) {
            companyResponse.setId(company.getId());
        }
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setLocatedCountry(company.getLocatedCountry());
        return companyResponse;
    }
}
