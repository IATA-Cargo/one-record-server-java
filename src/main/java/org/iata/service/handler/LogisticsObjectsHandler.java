package org.iata.service.handler;

import org.iata.api.model.AuditTrail;
import org.iata.api.model.Create;
import org.iata.api.model.PatchRequest;
import org.iata.cargo.model.LogisticsObject;
import org.iata.model.AccessControlList;
import org.iata.service.AccessControlListService;
import org.iata.service.AuditTrailsService;
import org.iata.service.LogisticsObjectsService;
import org.iata.util.Utils;
import org.springframework.stereotype.Service;
import org.wc.acl.model.Access;
import org.wc.acl.model.Authorization;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class LogisticsObjectsHandler {

  private final LogisticsObjectsService logisticsObjectsService;
  private final AuditTrailsService auditTrailsService;
  private final AccessControlListService accessControlListService;

  @Inject
  public LogisticsObjectsHandler(LogisticsObjectsService logisticsObjectsService, AuditTrailsService auditTrailsService, AccessControlListService accessControlListService) {
    this.logisticsObjectsService = logisticsObjectsService;
    this.auditTrailsService = auditTrailsService;
    this.accessControlListService = accessControlListService;
  }

  public LogisticsObject handleAddLogisticsObject(LogisticsObject logisticsObject, String companyIdentifier) {
    logisticsObject.setCompanyIdentifier(logisticsObject.getCompanyIdentifier());
    String loid = companyIdentifier + "/" + logisticsObject.getClass().getSimpleName() + "_" + Utils.getRandomNumberString();
    logisticsObject.setId(loid);
    logisticsObjectsService.addLogisticsObject((logisticsObject));

    // Save create object in the audit trail
    AuditTrail auditTrail = new AuditTrail();
    String auditTrailId = loid + "/auditTrail";
    auditTrail.setId(auditTrailId);
    Create create = new Create();
    create.setId(auditTrailId + "/create");
    create.setLo(logisticsObject);
    auditTrail.setCreate(create);
    auditTrail.setLogisticsObjectRef(loid);
    auditTrailsService.addAuditTrail(auditTrail);

    // Create ACL
    // TODO
    AccessControlList accessControlList = new AccessControlList();
    accessControlList.setLogisticsObjectRef(loid);
    List<Authorization> authorizationList = new ArrayList<>();
    Authorization authorization = new Authorization();
    authorization.setId(loid + "/acl/authorization_1");
    authorizationList.add(authorization);
    accessControlList.setAuthorizations(new HashSet<>(authorizationList));
    accessControlListService.addAccessControlList(accessControlList);

    return logisticsObject;
  }

  public void handleUpdateLogisticsObject(PatchRequest patchRequest) {
    logisticsObjectsService.updateLogisticsObject(patchRequest);
    auditTrailsService.updateAuditTrail(patchRequest);
  }

}
