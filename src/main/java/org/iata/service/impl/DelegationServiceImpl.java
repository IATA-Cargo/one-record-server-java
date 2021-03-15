package org.iata.service.impl;

import org.iata.api.model.DelegationRequest;
import org.iata.repository.DelegationRequestRepository;
import org.iata.service.DelegationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class DelegationServiceImpl implements DelegationService {

  private static final Logger LOG = LoggerFactory.getLogger(DelegationServiceImpl.class);

  private final DelegationRequestRepository delegationRequestRepository;

  @Inject
  public DelegationServiceImpl(DelegationRequestRepository delegationRequestRepository) {
    this.delegationRequestRepository = delegationRequestRepository;
  }

  @Override
  public void delegateAccess(DelegationRequest delegationRequest) {
    // TODO for each
    final String delegationId = delegationRequest.getTargetLogisticsObjects() + "#" + delegationRequest.getTargetCompanies() + "#" + delegationRequest.getAction();
    if (delegationRequest.getAction().equalsIgnoreCase("DELEGATE")) {
      delegationRequest.setId(delegationId);
      delegationRequestRepository.save(delegationRequest);
    } else {
      delegationRequestRepository.deleteById(delegationId);
    }
    // TODO when security is implemented correctly, handle the delegation of access
  }
}
