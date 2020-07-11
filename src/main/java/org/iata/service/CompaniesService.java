package org.iata.service;

import org.iata.api.model.CompanyInformation;

import java.util.List;

public interface CompaniesService {

  void addCompany(CompanyInformation company, String companyIdentifierForIoL, String companyId);

  List<CompanyInformation> getCompanies();

  CompanyInformation findByCompanyId(String companyId);

  CompanyInformation findById(String id);

  void deleteById(String id);

}
