package org.iata.service.impl;

import org.iata.api.model.AuditTrail;
import org.iata.api.model.ChangeRequest;
import org.iata.api.model.PatchRequest;
import org.iata.exception.LogisticsObjectNotFoundException;
import org.iata.repository.AuditTrailRepository;
import org.iata.service.AuditTrailsService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    AuditTrail auditTrail = auditTrailRepository
        .findByLogisticsObjectRef(patchRequest.getLogisticsObjectRef().getLogisticsObjectId())
        .stream().findFirst().orElseThrow(LogisticsObjectNotFoundException::new);
    Set<ChangeRequest> changeRequests = Optional.ofNullable(auditTrail.getChangeRequests()).orElse(new HashSet<>());
    ChangeRequest changeRequest = new ChangeRequest();
    changeRequest.setTimestamp(new Date());
    changeRequest.setCompanyId(patchRequest.getRequestorCompanyIdentifier());
    changeRequest.setPatchRequest(patchRequest);
    changeRequests.add(changeRequest);
    auditTrail.setChangeRequests(changeRequests);
    auditTrailRepository.save(auditTrail);
  }

  @Override
  public AuditTrail findById(String auditTrailId, LocalDate updatedFrom, LocalDate updatedTo) {
    AuditTrail auditTrail = auditTrailRepository.findById(auditTrailId).orElse(null);
    if (auditTrail != null) {
      List<ChangeRequest> changeRequests = Optional.ofNullable(auditTrail.getChangeRequests()).map(a -> a
          .stream()
          .filter(changeRequest -> (updatedFrom != null && changeRequest.getTimestamp().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(updatedFrom))
              && (updatedTo != null && changeRequest.getTimestamp().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(updatedTo)))
          .collect(Collectors.toList())).orElse(new ArrayList<>());
      auditTrail.setChangeRequests(new HashSet<>(changeRequests));
    }

    return auditTrail;
  }

}
