package org.iata.repository;

import org.iata.cargo.model.LogisticsObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LogisticsObjectsRepository extends MongoRepository<LogisticsObject, String> {

//    @Query(value = "{_id: { $regex: /^?0.*/} })")
//    List<LogisticsObject> findByIdStartsWith(String id);
}