package com.example.finally_project_boot.repository;

import com.example.finally_project_boot.model.entity.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select c from Company c where upper(c.companyName) like concat('%',:text,'%') ")
    List<Company> searchCompanyByName(@Param("text")String text, Pageable pageable);

}