package com.example.finally_project_boot.service;

import com.example.finally_project_boot.dto.request.CompanyRequest;
import com.example.finally_project_boot.dto.response.CompanyResponse;
import com.example.finally_project_boot.model.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getAllCompanies();

    CompanyResponse saveCompany(CompanyRequest companyRequest);

    Optional<Company> findById(Long id);

    CompanyResponse updateCompany(Long id, CompanyRequest companyRequest);

    CompanyResponse deleteById(Long id);
}
