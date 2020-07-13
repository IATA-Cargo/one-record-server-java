package org.iata.service;

import org.iata.api.model.Memento;
import org.iata.api.model.Timemap;

import java.util.Date;

public interface VersioningService {

  String addMemento(String currentUri, String loUri, Memento memento);

  Memento getMemento(String currentUri);

  void addTimemap(Timemap timemap);

  Timemap getTimemap(String timemapUri);

  Memento findMementoByDate(String loUri, Date dateTime);

}
