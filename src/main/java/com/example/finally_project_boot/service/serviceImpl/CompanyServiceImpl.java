package com.example.finally_project_boot.service.serviceImpl;

import com.example.finally_project_boot.dto.request.CompanyRequest;
import com.example.finally_project_boot.dto.response.CompanyResponse;
import com.example.finally_project_boot.dto.response.CompanyResponseView;
import com.example.finally_project_boot.model.entity.Company;
import com.example.finally_project_boot.model.mapper.CompanyMapper;
import com.example.finally_project_boot.repository.CompanyRepository;
import com.example.finally_project_boot.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public CompanyResponse saveCompany(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setCompanyName(companyRequest.getCompanyName());
        company.setLocatedCountry(companyRequest.getLocatedCountry());
       companyRepository.save(company);
        return new CompanyResponse(company.getId(),company.getCompanyName(),
                company.getLocatedCountry());
    }

    @Override
    public Optional<Company> findById(Long id) {
        boolean exist = companyRepository.existsById(id);
        if (!exist){
            throw new RuntimeException(
                    String.format("exist by id")
            );
        }
        return companyRepository.findById(id);
    }

    @Override
    public CompanyResponse updateCompany(Long id, CompanyRequest companyRequest) {
        boolean exists = companyRepository.existsById(id);
        Company company;
        if (!exists){
            throw  new RuntimeException(
                    String.format("update with id does not exists")
            );
        }
        company = companyMapper.mapToEntity(id,companyRequest);
        companyRepository.save(company);
        return companyMapper.mapToResponse(company);

    }

    @Override
    public CompanyResponse deleteById(Long id) {
        CompanyResponse companyResponse = getById(id);
        boolean exist = companyRepository.existsById(id);
        if (!exist) {
            throw new RuntimeException(
                    String.format("delete exist by id")
            );
        }
        companyRepository.deleteById(id);
        return companyResponse;
    }
    private CompanyResponse getById(Long id){
        return companyMapper.mapToResponse(companyRepository.findById(id).orElseThrow(()-> new RuntimeException(
                String.format("Author with id = does not exists")
        )));
    }

    public CompanyResponseView getCompanyPagination(String text,int page,int size){
        CompanyResponseView companyResponseView = new CompanyResponseView();
        Pageable pageable = PageRequest.of(page-1, size);
        companyResponseView.setCompanyResponses(getCompanyPage(search(text,pageable)));
        companyResponseView.setCurrentPage(pageable.getPageNumber()+1);
        companyResponseView.setTotalPage(companyRepository.findAll(pageable).getTotalPages());
        return companyResponseView;
    }

    public List<CompanyResponse>getCompanyPage(List<Company> companies){
        List<CompanyResponse> companyResponses = new ArrayList<>();
        for (Company company: companies){
            companyResponses.add(companyMapper.mapToResponse(company));
        }
        return companyResponses;
    }

    public List<Company>search(String name,Pageable pageable){
        String text = name==null? "" : name;
        return companyRepository.searchCompanyByName(text,pageable);
    }
}
