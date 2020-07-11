package org.iata.service;

import org.iata.api.model.PatchRequest;
import org.iata.cargo.model.Event;
import org.iata.cargo.model.LogisticsObject;
import org.iata.model.AccessControlList;

import java.util.List;

public interface LogisticsObjectsService {

  void addLogisticsObject(LogisticsObject logisticsObject);

  LogisticsObject findById(String id);

  List<LogisticsObject> findByCompanyIdentifier(String companyId);

  void updateLogisticsObject(PatchRequest patchRequest);

  void addEvent(Event event);

  List<Event> findEvents(String loId);
  
}
