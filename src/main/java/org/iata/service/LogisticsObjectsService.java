package org.iata.service;

import org.iata.api.model.PatchRequest;
import org.iata.cargo.model.Event;
import org.iata.cargo.model.LogisticsObject;

import java.util.List;

public interface LogisticsObjectsService {

  void addLogisticsObject(LogisticsObject logisticsObject);

  LogisticsObject findById(String id);

  List<LogisticsObject> findByCompanyIdentifier(String companyId);
  List<LogisticsObject> findByCompanyIdentifierAndLogisticsObjectType(String companyId, String logisticsObjectType);

  void updateLogisticsObject(PatchRequest patchRequest);

  void addEvent(Event event, String loUri);

  List<Event> findEvents(String loId);
  
}
