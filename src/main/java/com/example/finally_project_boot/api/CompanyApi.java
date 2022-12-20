package com.example.finally_project_boot.api;

import com.example.finally_project_boot.dto.request.CompanyRequest;
import com.example.finally_project_boot.dto.response.CompanyResponse;
import com.example.finally_project_boot.dto.response.CompanyResponseView;
import com.example.finally_project_boot.model.entity.Company;
import com.example.finally_project_boot.service.CompanyService;
import com.example.finally_project_boot.service.serviceImpl.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class CompanyApi {

     public final CompanyService companyService;
     private final CompanyServiceImpl companyServiceImpl;


     @GetMapping("/all")
     public List<Company> allCompany(){
         return companyService.getAllCompanies();
     }

    @PostMapping
    public CompanyResponse saveCompany(@RequestBody CompanyRequest companyRequest){
        return companyService.saveCompany(companyRequest);
    }

    @GetMapping("/{id}")
    public Optional<Company> getByIdCompany(@PathVariable Long id){
        return companyService.findById(id);
    }



    @DeleteMapping( "/delete/{id}")
    public CompanyResponse deleteCompany(@PathVariable("id") Long id){
        return  companyService.deleteById(id);
    }


    @PatchMapping("/update/{id}")
    public CompanyResponse updateCompany(@PathVariable("id") Long id, @RequestBody CompanyRequest companyRequest){
        return companyService.updateCompany(id,companyRequest);
    }

    @GetMapping("/page")
    public CompanyResponseView companyPage(@RequestParam(value = "text",required = false) String text,
                                           @RequestParam int page,
                                           @RequestParam int size){
         return companyServiceImpl.getCompanyPagination(text,page,size);
    }
}
