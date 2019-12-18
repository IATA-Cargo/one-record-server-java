package org.iata.service;

import org.iata.model.LogisticsObject;

import java.util.List;

public interface LogisticsObjectsService {

  void addLogisticsObject(LogisticsObject logisticsObject);

  List<LogisticsObject> findByCompanyId(String companyId);
}
