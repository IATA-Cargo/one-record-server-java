package org.iata.service.handler;

import org.iata.api.model.AuditTrail;
import org.iata.model.LogisticsObject;
import org.iata.service.AuditTrailsService;
import org.iata.service.LogisticsObjectsService;
import org.springframework.stereotype.Service;

@Service
public class LogisticsObjectsHandler {

  private final LogisticsObjectsService logisticsObjectsService;
  private final AuditTrailsService auditTrailsService;

  public LogisticsObjectsHandler(LogisticsObjectsService logisticsObjectsService, AuditTrailsService auditTrailsService) {
    this.logisticsObjectsService = logisticsObjectsService;
    this.auditTrailsService = auditTrailsService;
  }

  public void handleAddLogisticsObject(LogisticsObject logisticsObject) {
    logisticsObjectsService.addLogisticsObject(logisticsObject);
    auditTrailsService.addAuditTrail(new AuditTrail()); // TODO
  }

  public void handleUpdateLogisticsObject(LogisticsObject logisticsObject) {
    logisticsObjectsService.updateLogisticsObject(logisticsObject);
    auditTrailsService.updateAuditTrail(logisticsObject.getId()); // TODO
  }

}
