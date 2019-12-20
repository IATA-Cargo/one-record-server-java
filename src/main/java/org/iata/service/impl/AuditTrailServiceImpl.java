package org.iata.service.impl;

import org.iata.model.AuditTrail;
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
  public List<AuditTrail> findByLogisticsObjectId(String logisticsObjectId) {
    return auditTrailRepository.findByLogisticsObjectId(logisticsObjectId);
  }

  @Override
  public void updateAuditTrail(String logisticsObjectId) {
    AuditTrail auditTrail = auditTrailRepository.findByLogisticsObjectId(logisticsObjectId).get(0); // TODO
    auditTrailRepository.save(auditTrail);
  }

}
