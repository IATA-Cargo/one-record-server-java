package org.iata.service.handler;

import org.iata.api.model.AuditTrail;
import org.iata.api.model.LogisticsObjectRef;
import org.iata.api.model.Memento;
import org.iata.api.model.PatchRequest;
import org.iata.api.model.Timemap;
import org.iata.cargo.model.LogisticsObject;
import org.iata.model.AccessControlList;
import org.iata.service.AccessControlListService;
import org.iata.service.AuditTrailsService;
import org.iata.service.LogisticsObjectsService;
import org.iata.service.VersioningService;
import org.iata.util.Utils;
import org.springframework.stereotype.Service;
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
  private final VersioningService versioningService;

  @Inject
  public LogisticsObjectsHandler(LogisticsObjectsService logisticsObjectsService,
                                 AuditTrailsService auditTrailsService,
                                 AccessControlListService accessControlListService,
                                 VersioningService versioningService) {
    this.logisticsObjectsService = logisticsObjectsService;
    this.auditTrailsService = auditTrailsService;
    this.accessControlListService = accessControlListService;
    this.versioningService = versioningService;
  }

  public LogisticsObject handleAddLogisticsObject(LogisticsObject logisticsObject, String companyIdentifier) {
    logisticsObject.setCompanyIdentifier(logisticsObject.getCompanyIdentifier());
    String loid = companyIdentifier + "/" + logisticsObject.getClass().getSimpleName() + "_" + Utils.getRandomNumberString();
    logisticsObject.setId(loid);
    logisticsObjectsService.addLogisticsObject((logisticsObject));

    // Save create object in the audit trail
    saveAuditTrailForLo(logisticsObject, loid);

    // Create ACL
    createAclForLo(loid);

    // Create Timemap
    createTimemapForLo(loid);

    return logisticsObject;
  }

  private void saveAuditTrailForLo(LogisticsObject logisticsObject, String loid) {
    AuditTrail auditTrail = new AuditTrail();
    String auditTrailId = loid + "/auditTrail";
    auditTrail.setId(auditTrailId);
    Memento initialLo = new Memento();

    // TODO fonctionality for creating snapshots/mementos
    initialLo.setId(auditTrailId + "/loInitialSnapshot");
    auditTrail.setLoInitialSnapshot(initialLo);
    LogisticsObjectRef logisticsObjectRef = new LogisticsObjectRef();
    logisticsObjectRef.setLogisticsObjectId(loid);
    auditTrail.setLogisticsObjectRef(logisticsObjectRef);
    auditTrailsService.addAuditTrail(auditTrail);
  }

  private void createAclForLo(String loid) {
    // TODO
    AccessControlList accessControlList = new AccessControlList();
    accessControlList.setId(loid + "/acl");
    accessControlList.setLogisticsObjectRef(loid);
    List<Authorization> authorizationList = new ArrayList<>();
    Authorization authorization = new Authorization();
    authorization.setId(loid + "/acl/authorization_1");
    authorizationList.add(authorization);
    accessControlList.setAuthorizations(new HashSet<>(authorizationList));
    accessControlListService.addAccessControlList(accessControlList);
  }

  private void createTimemapForLo(String loid) {
    Timemap timemap = new Timemap();
    timemap.setId(loid + "/timemap");
    timemap.setOriginal(loid);
    timemap.setTimegate(loid + "/timegate");
    versioningService.addTimemap(timemap);
  }

  public void handleUpdateLogisticsObject(PatchRequest patchRequest) {
    logisticsObjectsService.updateLogisticsObject(patchRequest);
    auditTrailsService.updateAuditTrail(patchRequest);
  }

}
