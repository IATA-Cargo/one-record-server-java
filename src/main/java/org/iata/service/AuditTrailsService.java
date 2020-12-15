package org.iata.service;

import org.iata.api.model.AuditTrail;
import org.iata.api.model.PatchRequest;

import java.time.LocalDate;
import java.util.List;

public interface AuditTrailsService {

  void addAuditTrail(AuditTrail auditTrail);

  List<AuditTrail> findByLogisticsObjectRef(String logisticsObjectId);

  void updateAuditTrail(PatchRequest patchRequest);

  AuditTrail findById(String auditTrailId, LocalDate updatedFrom, LocalDate updatedTo);

}
