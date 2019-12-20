package org.iata.service;

import org.iata.model.DelegationRequest;

public interface DelegationService {

  void delegateAccess(DelegationRequest delegationRequest);
}
