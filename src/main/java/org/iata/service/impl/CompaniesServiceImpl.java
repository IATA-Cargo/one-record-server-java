package org.iata.service.impl;

import org.iata.model.CompanyInformation;
import org.iata.repository.CompaniesRepository;
import org.iata.service.CompaniesService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class CompaniesServiceImpl implements CompaniesService {

  private final CompaniesRepository companiesRepository;

  @Inject
  public CompaniesServiceImpl(CompaniesRepository companiesRepository) {
    this.companiesRepository = companiesRepository;
  }

  @Override
  public void addCompany(CompanyInformation companyInformation) {
    companiesRepository.save(companyInformation);
  }

  @Override
  public List<CompanyInformation> getCompanies() {
    return companiesRepository.findAll();
  }

  @Override
  public void updateCompany(CompanyInformation companyInformation) {
    companiesRepository.save(companyInformation);
  }

  @Override
  public CompanyInformation findByCompanyId(String companyId) {
    return companiesRepository.findByCompanyId(companyId);
  }

  @Override
  public void deleteByCompanyId(String companyId) {
    companiesRepository.deleteByCompanyId(companyId);
  }

}
