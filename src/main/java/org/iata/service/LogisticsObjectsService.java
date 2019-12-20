package org.iata.service;

import org.iata.model.LogisticsObject;

import java.util.List;

public interface LogisticsObjectsService {

  void addLogisticsObject(LogisticsObject logisticsObject);

  LogisticsObject findById(String id);

  List<LogisticsObject> findByCompanyId(String companyId);

  void updateLogisticsObject(LogisticsObject logisticsObject);

}
