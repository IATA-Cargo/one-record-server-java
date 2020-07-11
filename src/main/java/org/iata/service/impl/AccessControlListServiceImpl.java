package org.iata.service.impl;

import org.iata.model.AccessControlList;
import org.iata.repository.AccessControlListRepository;
import org.iata.service.AccessControlListService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class AccessControlListServiceImpl implements AccessControlListService {

  private final AccessControlListRepository accessControlListRepository;

  @Inject
  public AccessControlListServiceImpl(AccessControlListRepository accessControlListRepository) {
    this.accessControlListRepository = accessControlListRepository;
  }

  @Override
  public void addAccessControlList(AccessControlList accessControlList) {
    accessControlList.setId(accessControlList.getLogisticsObjectRef() + "/acl");
    accessControlListRepository.save(accessControlList);
  }

  @Override
  public List<AccessControlList> findByLogisticsObjectRef(String logisticsObjectRef) {
    return accessControlListRepository.findByLogisticsObjectRef(logisticsObjectRef);
  }

  @Override
  public void updateAccessControlList(String logisticsObjectId) {
    AccessControlList accessControlList = accessControlListRepository.findByLogisticsObjectRef(logisticsObjectId).get(0); // TODO
    accessControlListRepository.save(accessControlList);
  }

}
