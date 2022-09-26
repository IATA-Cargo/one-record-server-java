package org.iata.service.impl;

import com.google.common.collect.Lists;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import org.iata.api.model.Operation;
import org.iata.api.model.PatchRequest;
import org.iata.cargo.model.Event;
import org.iata.cargo.model.LogisticsObject;
import org.iata.exception.PatchRequestOperationPathNotFoundException;
import org.iata.exception.UnprocessablePatchRequestException;
import org.iata.exception.UnsupportedPatchRequestOperationException;
import org.iata.exception.UnsupportedPatchRequestOperationObjectException;
import org.iata.repository.LogisticsObjectsRepository;
import org.iata.service.LogisticsObjectsService;
import org.iata.util.Utils;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class LogisticsObjectsServiceImpl implements LogisticsObjectsService {

    private final LogisticsObjectsRepository logisticsObjectsRepository;

    @Inject
    public LogisticsObjectsServiceImpl(LogisticsObjectsRepository logisticsObjectsRepository) {
        this.logisticsObjectsRepository = logisticsObjectsRepository;
    }

    @Override
    public void addLogisticsObject(LogisticsObject logisticsObject) {
        logisticsObjectsRepository.save(logisticsObject);
    }

    @Override
    public LogisticsObject findById(String id) {
        return logisticsObjectsRepository.findById(id).orElse(null);
    }

    @Override
    public List<LogisticsObject> findByCompanyIdentifier(String companyId) {
        return logisticsObjectsRepository.findByCompanyIdentifier(companyId);
    }

    @Override
    public List<LogisticsObject> findByCompanyIdentifierAndLogisticsObjectType(String companyId, String logisticsObjectType) {
        return logisticsObjectsRepository.findByCompanyIdentifierAndTypes(companyId, logisticsObjectType);
    }


    public Field getClassFieldByOWLObjectProperty(Class<?> cls, String iri) {
        AtomicReference<Field> field = new AtomicReference<>();
        Arrays.stream(cls.getDeclaredFields()).forEach(df -> Arrays.stream(df.getDeclaredAnnotations()).forEach(da -> {
            if (
                    (da.annotationType() == OWLObjectProperty.class && ((OWLObjectProperty) da).iri().equals(iri)) ||
                            (da.annotationType() == OWLDataProperty.class && ((OWLDataProperty) da).iri().equals(iri))

            ) {
                field.set(df);
            }
        }));
        return field.get();
    }

    @Override
    public void updateLogisticsObject(PatchRequest patchRequest) {
        LogisticsObject logisticsObject = logisticsObjectsRepository.findById(patchRequest.getLogisticsObjectRef().getLogisticsObjectId()).orElse(null);
        if (logisticsObject != null) {
            if (logisticsObject.getRevision() != Integer.parseInt(patchRequest.getRevision())) {
                throw new UnprocessablePatchRequestException();
            }
            applyOperationsToLogisticsObject(patchRequest.getOperations(), logisticsObject);
            logisticsObjectsRepository.save(logisticsObject);
        }
    }


    public LogisticsObject applyOperationsToLogisticsObject(Set<Operation> operations, LogisticsObject logisticsObject) {
        PropertyAccessor logisticsObjectPropertyAccessor = PropertyAccessorFactory.forBeanPropertyAccess(logisticsObject);
        operations.forEach(operation -> {
            Field affectedField = getClassFieldByOWLObjectProperty(logisticsObject.getClass(), operation.getP());
            if (affectedField != null) {
                switch (operation.getOp()) {
                    case "del":
                        if (logisticsObjectPropertyAccessor.getPropertyValue(affectedField.getName()) == null || (affectedField.getType() == Set.class &&
                                ((Set<?>) Objects.requireNonNull(logisticsObjectPropertyAccessor.getPropertyValue(affectedField.getName()))).isEmpty())) {
                            // You cannot delete something that does not exist
                            throw new PatchRequestOperationPathNotFoundException();
                        }
                        logisticsObjectPropertyAccessor.setPropertyValue(affectedField.getName(), null);
                        break;
                    case "add":
                        Object newValue = operation.getO().getObject();
                        if (affectedField.getType() == Set.class && newValue != null) {
                            @SuppressWarnings(value = "unchecked")
                            HashSet<Object> fieldValues = (HashSet<Object>) logisticsObjectPropertyAccessor.getPropertyValue(affectedField.getName());
                            if (fieldValues == null) {
                                fieldValues = new HashSet<>();
                            }
                            fieldValues.add(newValue);
                            logisticsObjectPropertyAccessor.setPropertyValue(affectedField.getName(), fieldValues);
                        } else if (newValue != null && newValue.getClass() == String.class) {
                            //check if value is null, because this is not an implementation of the replace operation
                            if (logisticsObjectPropertyAccessor.getPropertyValue(affectedField.getName()) != null) {
                                throw new PatchRequestOperationPathNotFoundException();
                            }

                            switch (affectedField.getType().getSimpleName()) {
                                case "String":
                                    logisticsObjectPropertyAccessor.setPropertyValue(affectedField.getName(), newValue);
                                    break;
                                case "Date":
                                    logisticsObjectPropertyAccessor.setPropertyValue(affectedField.getName(), Date.from(Instant.parse((String) newValue)));
                                    break;
                                case "Integer":
                                    logisticsObjectPropertyAccessor.setPropertyValue(affectedField.getName(), Integer.valueOf((String) newValue));
                                    break;
                                case "Double":
                                    logisticsObjectPropertyAccessor.setPropertyValue(affectedField.getName(), Double.valueOf((String) newValue));
                                    break;
                                case "Boolean":
                                    logisticsObjectPropertyAccessor.setPropertyValue(affectedField.getName(), Boolean.valueOf((String) newValue));
                                    break;
                            }
                        } else {
                            throw new UnsupportedPatchRequestOperationObjectException();
                        }
                        break;
                    default:
                        throw new UnsupportedPatchRequestOperationException();
                }
            } else {
                throw new PatchRequestOperationPathNotFoundException();
            }
        });
        return logisticsObject;
    }


    @Override
    public void addEvent(Event event, String loUri) {
        LogisticsObject logisticsObject = logisticsObjectsRepository.findById(loUri).orElse(null);
        if (logisticsObject != null) {
            event.setId(loUri + "/Event_" + Utils.getRandomNumberString());
            Set<Event> events = Optional.ofNullable(logisticsObject.getEvents()).orElse(new HashSet<>());
            events.add(event);
            logisticsObject.setEvents(events);
            logisticsObjectsRepository.save(logisticsObject);
        }
    }

    @Override
    public List<Event> findEvents(String loUri) {
        Set<Event> set = logisticsObjectsRepository.findById(loUri)
                .map(logisticsObject -> Optional.ofNullable(logisticsObject.getEvents()).orElse(new HashSet<>()))
                .orElse(new HashSet<>());
        return Lists.newArrayList(set);
    }

}
