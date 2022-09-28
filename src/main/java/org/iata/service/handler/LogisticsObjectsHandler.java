package org.iata.service.handler;

import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import org.iata.api.model.AuditTrail;
import org.iata.api.model.LogisticsObjectRef;
import org.iata.api.model.Memento;
import org.iata.api.model.PatchRequest;
import org.iata.api.model.Timemap;
import org.iata.cargo.model.LogisticsObject;
import org.iata.model.AccessControlList;
import org.iata.model.enums.EventType;
import org.iata.service.*;
import org.iata.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wc.acl.model.Authorization;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class LogisticsObjectsHandler {

    private final LogisticsObjectsService logisticsObjectsService;
    private final AuditTrailsService auditTrailsService;
    private final AccessControlListService accessControlListService;
    private final VersioningService versioningService;

    private final SubscriptionsService subscriptionsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LogisticsObjectsHandler.class);

    @Inject
    public LogisticsObjectsHandler(LogisticsObjectsService logisticsObjectsService,
                                   AuditTrailsService auditTrailsService,
                                   AccessControlListService accessControlListService,
                                   VersioningService versioningService,
                                   SubscriptionsService subscriptionsService) {
        this.logisticsObjectsService = logisticsObjectsService;
        this.auditTrailsService = auditTrailsService;
        this.accessControlListService = accessControlListService;
        this.versioningService = versioningService;
        this.subscriptionsService = subscriptionsService;
    }

    public LogisticsObject handleAddLogisticsObject(LogisticsObject logisticsObject, String companyIdentifier) {
        logisticsObject.setCompanyIdentifier(companyIdentifier);
        String loid = logisticsObject.getId();
        if (loid != null && Utils.isValidURL(loid) && Utils.containsServerAuthority(loid)) {
            //do nothing
        } else {
            loid = companyIdentifier + "/" + logisticsObject.getClass().getSimpleName().toLowerCase() + "-" + Utils.getRandomNumberString();
        }
        logisticsObject.setId(Utils.replaceAuthorityWithServerAuthority(Utils.toKebabCase(loid)));
        logisticsObjectsService.addLogisticsObject((logisticsObject));

        // Save create object in the audit trail
        saveAuditTrailForLo(logisticsObject, loid);

        // Create ACL
        createAclForLo(loid);

        // Create Timemap
        createTimemapForLo(loid);

        // Notify Subscribers
        Annotation logisticsObjectClassAnnotation = Arrays.stream(logisticsObject.getClass().getDeclaredAnnotations()).filter(a -> a.annotationType() == OWLClass.class).findAny().orElse(null);
        if (logisticsObjectClassAnnotation != null && logisticsObjectClassAnnotation.annotationType() == OWLClass.class) {
            subscriptionsService.notifySubscribers(EventType.OBJECT_CREATED, ((OWLClass) logisticsObjectClassAnnotation).iri(), logisticsObject.getId());
        } else {
            LOGGER.error("Could not identify IRI of LogisticsObject {}", logisticsObject.getId());
        }

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
        loid = Utils.replaceAuthorityWithServerAuthority(Utils.toKebabCase(loid));
        Timemap timemap = new Timemap();
        timemap.setId(loid + "/timemap");
        timemap.setOriginal(loid);
        timemap.setTimegate(loid + "/timegate");
        versioningService.addTimemap(timemap);
    }

    public void handleUpdateLogisticsObject(PatchRequest patchRequest) {
        logisticsObjectsService.updateLogisticsObject(patchRequest);
        auditTrailsService.updateAuditTrail(patchRequest);
        subscriptionsService.notifySubscribers(EventType.OBJECT_UPDATED, patchRequest.getLogisticsObjectRef().getLogisticsObjectType(), patchRequest.getLogisticsObjectRef().getLogisticsObjectId());
    }

}
