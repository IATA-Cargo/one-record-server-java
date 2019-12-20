package org.iata.service;

import org.iata.model.AuditTrail;

import java.util.List;

public interface AuditTrailsService {

  void addAuditTrail(AuditTrail auditTrail);

  List<AuditTrail> findByLogisticsObjectId(String logisticsObjectId);

  void updateAuditTrail(String logisticsObjectId);

}
