package org.iata.service.impl;

import org.iata.model.LogisticsObject;
import org.iata.repository.LogisticsObjectsRepository;
import org.iata.service.LogisticsObjectsService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class LogisticsObjectsServiceImpl implements LogisticsObjectsService {

  private final LogisticsObjectsRepository logisticsObjectsRepository;

  @Inject
  public LogisticsObjectsServiceImpl(LogisticsObjectsRepository logisticsObjectsRepository) {
    this.logisticsObjectsRepository = logisticsObjectsRepository;
  }

  @Override
  public void addLogisticsObject(LogisticsObject logisticsObject) {
    logisticsObjectsRepository.save(logisticsObject);
  }

  @Override
  public List<LogisticsObject> findByCompanyId(String companyId) {
    return logisticsObjectsRepository.findByCompanyId(companyId);
  }

}
