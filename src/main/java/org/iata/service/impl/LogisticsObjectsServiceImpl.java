package org.iata.service.impl;

import com.google.common.collect.Lists;
import org.iata.api.model.PatchRequest;
import org.iata.cargo.model.Event;
import org.iata.cargo.model.LogisticsObject;
import org.iata.repository.LogisticsObjectsRepository;
import org.iata.service.LogisticsObjectsService;
import org.iata.util.Utils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
  public void updateLogisticsObject(PatchRequest patchRequest) {
    LogisticsObject logisticsObject = logisticsObjectsRepository.findById(patchRequest.getLogisticsObjectRef().getLogisticsObjectId()).orElse(null);
    if (logisticsObject != null) {
      patchRequest.getOperations()
          .forEach(operation -> {

          });
      // TODO
      logisticsObjectsRepository.save(logisticsObject);
    }
  }

  @Override
  public void addEvent(Event event, String loUri) {
    LogisticsObject logisticsObject = logisticsObjectsRepository.findById(loUri).orElse(null);
    event.setId(loUri + "/Event_" + Utils.getRandomNumberString());
    if (logisticsObject != null) {
      Set<Event> events = Optional.ofNullable(logisticsObject.getEvents()).orElse(new HashSet<>());
      events.add(event);
      logisticsObject.setEvents(events);
      logisticsObjectsRepository.save(logisticsObject);
    }
  }

  @Override
  public List<Event> findEvents(String loUri) {
    Set<Event> set =  logisticsObjectsRepository.findById(loUri)
     .map(logisticsObject -> Optional.ofNullable(logisticsObject.getEvents()).orElse(new HashSet<>()))
     .orElse(new HashSet<>());
    return Lists.newArrayList(set);
  }

}
