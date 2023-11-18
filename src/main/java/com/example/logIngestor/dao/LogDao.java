package com.example.logIngestor.dao;

import com.example.logIngestor.entity.Log;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class LogDao {

  private final MongoTemplate mongoTemplate;

  public static final List<String> ALLOWED_SEARCH_KEYS = Arrays.asList("level","message","resourceId","timestamp","traceId","spanId","commit","meta.parentResourceId");



  public List<Log> searchLogs(Map<String,String> searchCriteria){
    Criteria criteria = new Criteria();

    for(Map.Entry<String,String> entry : searchCriteria.entrySet()){
      String key = entry.getKey();
      String value = entry.getValue();

      if(ALLOWED_SEARCH_KEYS.contains(key)){
        criteria.and(key).is(value);
      }

    }

    Query query = new Query(criteria);

    return mongoTemplate.find(query, Log.class, "log");
  }
}
