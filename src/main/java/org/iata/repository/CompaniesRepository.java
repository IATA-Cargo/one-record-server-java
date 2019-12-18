package org.iata.repository;

import org.iata.model.CompanyInformation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompaniesRepository extends MongoRepository<CompanyInformation, String> {

  CompanyInformation findByCompanyId(String companyId);
  void deleteByCompanyId(String companyId);

}