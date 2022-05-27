package org.iata.service.impl;

import org.iata.api.model.CompanyInformation;
import org.iata.cargo.model.*;
import org.iata.repository.CompaniesRepository;
import org.iata.service.CompaniesService;
import org.iata.util.Utils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service

public class CompaniesServiceImpl implements CompaniesService {

  private final CompaniesRepository companiesRepository;

  @Inject
  public CompaniesServiceImpl(CompaniesRepository companiesRepository) {
    this.companiesRepository = companiesRepository;
  }

  @Override
  public void addCompany(CompanyInformation companyInformation, String companyIdentifierForIoL, String companyId) {
    companyInformation.setId(companyIdentifierForIoL);
    companyInformation.setCompanyId(companyId);

    Company company = companyInformation.getCompany();
    company.setId(companyIdentifierForIoL + "/company");

    CompanyBranch branch = company.getBranch();
    if (branch != null) {
      branch.setId(company.getId() + "/branch");
      Location location = branch.getLocation();

      if (location != null) {
        location.setId(branch.getId() + "/location");

        Optional.ofNullable(location.getGeolocation()).ifPresent(geolocation1 -> {
          geolocation1.setId(location.getId() + "/geolocation");
          location.setGeolocation(geolocation1);
        });

        Optional.ofNullable(location.getAddress()).ifPresent(address -> {
          address.setId(location.getId() + "/address");
          Optional.ofNullable(address.getCountry()).ifPresent(country -> {
            country.setId(address.getId() + "/country");
            address.setCountry(country);
          });
          location.setAddress(address);
        });

        branch.setLocation(location);
      }

      Set<OtherIdentifier> otherIdentifierSet = Optional.ofNullable(branch.getOtherIdentifiers()).orElse(new HashSet<>());
      List<OtherIdentifier> otherIdentifiers = new ArrayList<>(otherIdentifierSet);
      otherIdentifiers.forEach(otherIdentifier -> {
        otherIdentifier.setId(branch.getId() + "/otherIdentifier" + Utils.increment(otherIdentifiers.indexOf(otherIdentifier), 1));
      });
      branch.setOtherIdentifiers(new HashSet<>(otherIdentifiers));

      Set<Person> personSet = Optional.ofNullable(branch.getContactPersons()).orElse(new HashSet<>());
      List<Person> persons = new ArrayList<>(personSet);
      persons.forEach(person -> {
        person.setId(branch.getId() + "/person" + Utils.increment(persons.indexOf(person), 1));

        Set<Contact> contactSet = Optional.ofNullable(person.getContact()).orElse(new HashSet<>());
        List<Contact> contacts = new ArrayList<>(contactSet);
        contactSet.forEach(contact -> {
          contact.setId(person.getId() + "/contact" + Utils.increment(contacts.indexOf(contact), 1));
        });
        person.setContact(new HashSet<>(contacts));

      });
      branch.setContactPersons(new HashSet<>(persons));
    }

//    company.setBranch(branch);
    companyInformation.setCompany(company);

    companiesRepository.save(companyInformation);
  }

  @Override
  public List<CompanyInformation> getCompanies() {
    return companiesRepository.findAll();
  }

  @Override
  public CompanyInformation findByCompanyId(String companyId) {
    return companiesRepository.findByCompanyId(companyId).orElse(null);
  }

  @Override
  public CompanyInformation findById(String id) {
    return companiesRepository.findById(id).orElse(null);
  }

  @Override
  public void deleteById(String id) {
    companiesRepository.deleteById(id);
  }

}
