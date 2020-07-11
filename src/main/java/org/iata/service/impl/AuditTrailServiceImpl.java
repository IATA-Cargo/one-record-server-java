package org.iata.service.impl;

import org.iata.api.model.AuditTrail;
import org.iata.api.model.ChangeRequest;
import org.iata.api.model.PatchRequest;
import org.iata.repository.AuditTrailRepository;
import org.iata.service.AuditTrailsService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
  public void updateAuditTrail(PatchRequest patchRequest) {
    AuditTrail auditTrail = auditTrailRepository.findByLogisticsObjectRef(patchRequest.getLogisticsObjectRef()).get(0);
    Set<ChangeRequest> changeRequests = Optional.ofNullable(auditTrail.getChangeRequests()).orElse(new HashSet<>());
    ChangeRequest changeRequest = new ChangeRequest();
    changeRequest.setTimestamp(new Date());
    changeRequest.setCompanyId(patchRequest.getRequestorCompanyIdentifier());
    changeRequest.getChangeRequest().add(patchRequest);
    changeRequests.add(changeRequest);
    auditTrail.setChangeRequests(changeRequests);
    auditTrailRepository.save(auditTrail);
  }

  @Override
  public AuditTrail findById(String auditTrailId) {
    return auditTrailRepository.findById(auditTrailId).orElse(null);
  }

}
