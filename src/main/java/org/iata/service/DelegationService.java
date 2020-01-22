package org.iata.service;

import org.iata.api.model.DelegationRequest;

public interface DelegationService {

  void delegateAccess(DelegationRequest delegationRequest);
}
