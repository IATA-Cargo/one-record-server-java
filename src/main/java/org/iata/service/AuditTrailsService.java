package org.iata.service;

import org.iata.api.model.AuditTrail;

import java.util.List;

public interface AuditTrailsService {

  void addAuditTrail(AuditTrail auditTrail);

  List<AuditTrail> findByLogisticsObjectRef(String logisticsObjectId);

  void updateAuditTrail(String logisticsObjectId);

}
