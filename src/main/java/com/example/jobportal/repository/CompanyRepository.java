package com.example.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobportal.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	public List<Company> findByCompanyName(String companyName);

}
