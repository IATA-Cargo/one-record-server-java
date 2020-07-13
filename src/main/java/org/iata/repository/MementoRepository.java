package org.iata.repository;

import org.iata.api.model.Memento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MementoRepository extends MongoRepository<Memento, String> {

  List<Memento> findByOriginal(String currentUri);

}