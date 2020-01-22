package org.iata.service.impl;

import org.iata.api.model.AuditTrail;
import org.iata.repository.AuditTrailRepository;
import org.iata.service.AuditTrailsService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class AuditTrailServiceImpl implements AuditTrailsService {

  private final AuditTrailRepository auditTrailRepository;

  @Inject
  public AuditTrailServiceImpl(AuditTrailRepository auditTrailRepository) {
    this.auditTrailRepository = auditTrailRepository;
  }

  @Override
  public void addAuditTrail(AuditTrail auditTrail) {
    auditTrailRepository.save(auditTrail);
  }

  @Override
  public List<AuditTrail> findByLogisticsObjectRef(String logisticsObjectRef) {
    return auditTrailRepository.findByLogisticsObjectRef(logisticsObjectRef);
  }

  @Override
  public void updateAuditTrail(String logisticsObjectRef) {
    AuditTrail auditTrail = auditTrailRepository.findByLogisticsObjectRef(logisticsObjectRef).get(0); // TODO
    auditTrailRepository.save(auditTrail);
  }

}
