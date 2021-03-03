package org.iata.service.impl;

import org.iata.api.model.Memento;
import org.iata.api.model.MementoEntry;
import org.iata.api.model.MementoList;
import org.iata.api.model.Mementos;
import org.iata.api.model.Timemap;
import org.iata.repository.MementoRepository;
import org.iata.repository.TimemapRepository;
import org.iata.service.VersioningService;
import org.iata.util.Utils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class VersioningServiceImpl implements VersioningService {

  private final MementoRepository mementoRepository;
  private final TimemapRepository timemapRepository;

  @Inject
  public VersioningServiceImpl(MementoRepository mementoRepository, TimemapRepository timemapRepository) {
    this.mementoRepository = mementoRepository;
    this.timemapRepository = timemapRepository;
  }

  @Override
  public String addMemento(String currentUri, String loUri, Memento memento) {
    final Date date = new Date();
    memento.setOriginal(loUri);
    memento.setCreated(date);
    final String mementoId = currentUri + "/" + Utils.generateUuid();
    memento.setId(mementoId);
    mementoRepository.save(memento);

    // Add memento to the Timemap and set it as last memento
    addLastMementoToTimemap(loUri, memento, mementoId);

    return mementoId;
  }

  private void addLastMementoToTimemap(String loUri, Memento memento, String mementoId) {
    Timemap timemap = timemapRepository.findById(loUri + "/timemap").orElse(new Timemap());
    Mementos mementos = Optional.ofNullable(timemap.getMementos()).orElse(new Mementos());
    mementos.setLastMemento(mementoId);

    if (mementos.getFirstMemento() == null) {
      mementos.setFirstMemento(mementoId);
    }

    MementoList mementoList = Optional.ofNullable(mementos.getList()).orElse(new MementoList());
    mementoList.setId(mementoId + "/mementoList");
    MementoEntry mementoEntry = new MementoEntry();
    mementoEntry.setId(mementoId + "/mementoList/mementoEntry_" + Utils.getRandomNumberString());
    Memento mem = new Memento();
    mem.setId(mementoId);
    mementoEntry.setMemento(mem);
    mementoEntry.setLabel(memento.getLabel());
    mementoEntry.setDatetime(memento.getCreated());
    if (mementoList.getMementoEntry() == null) {
      mementoList.setMementoEntry(new HashSet<>(Collections.singleton(mementoEntry)));
    } else {
      mementoList.getMementoEntry().add(mementoEntry);
    }
    mementos.setList(mementoList);
    timemap.setMementos(mementos);
    timemapRepository.save(timemap);
  }

  @Override
  public Memento getMemento(String mementoId) {
    return mementoRepository.findById(mementoId).orElse(null);
  }

  @Override
  public void addTimemap(Timemap timemap) {
    timemapRepository.save(timemap);
  }

  @Override
  public Timemap getTimemap(String timemapUri) {
    return timemapRepository.findById(timemapUri).orElse(null);
  }

  @Override
  public Memento findMementoByDate(String currentUri, Date dateTime) {
    List<Memento>  mementosForLo = mementoRepository.findByOriginal(currentUri);
    if (mementosForLo.size() == 0) {
      return null;
    }

    final List<Date> dates = mementosForLo.stream().map(Memento::getCreated).collect(Collectors.toList());
    final Date dateNearest = getDateNearest(dates, dateTime);

    return mementosForLo.stream().filter(memento -> memento.getCreated().equals(dateNearest)).findFirst().orElse(null);
  }

  private Date getDateNearest(List<Date> dates, Date targetDate) {
    NavigableSet<Date> dateToCompare = new TreeSet<>(dates);

    return Optional.ofNullable(dateToCompare.higher(targetDate))
        .orElseGet(() -> Optional.ofNullable(dateToCompare.lower(targetDate)).orElse(null));
  }

}
