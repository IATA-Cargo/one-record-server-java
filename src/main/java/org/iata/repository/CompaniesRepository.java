package org.iata.repository;

import org.iata.api.model.CompanyInformation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompaniesRepository extends MongoRepository<CompanyInformation, String> {

  Optional<CompanyInformation> findByCompanyId(String companyId);

  void deleteByCompanyId(String companyId);

}