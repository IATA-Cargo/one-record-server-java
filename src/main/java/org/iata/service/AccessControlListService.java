package org.iata.service;

import org.iata.model.AccessControlList;

import java.util.List;

public interface AccessControlListService {

  void addAccessControlList(AccessControlList accessControlList);

  List<AccessControlList> findByLogisticsObjectRef(String logisticsObjectId);

  void updateAccessControlList(String logisticsObjectId);

}
