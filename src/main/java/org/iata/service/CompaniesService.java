package org.iata.service;

import org.iata.model.CompanyInformation;

import java.util.List;

public interface CompaniesService {

  void addCompany(CompanyInformation company);

  List<CompanyInformation> getCompanies();

  void updateCompany(CompanyInformation companyInformation);

  CompanyInformation findByCompanyId(String companyId);

  void deleteByCompanyId(String companyId);
}
