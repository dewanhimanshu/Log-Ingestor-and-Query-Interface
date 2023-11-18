package com.example.logIngestor.dao;

import com.example.logIngestor.entity.Log;
import com.mongodb.client.model.Filters;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class LogDao {

  private final MongoTemplate mongoTemplate;

  public static final List<String> ALLOWED_SEARCH_KEYS = Arrays.asList("level","message","resourceId","timestamp","traceId","spanId","commit","meta.parentResourceId");



  public List<Log> searchLogs(Map<String,String> searchCriteria){
    TextCriteria textCriteria = null;
    Criteria criteria = new Criteria();



    for(Map.Entry<String,String> entry : searchCriteria.entrySet()){
      String key = entry.getKey();
      String value = entry.getValue();

      if(key.equals("text")){
        textCriteria = TextCriteria.forDefaultLanguage().matchingAny(value);
        continue;
      }

      if(key.equals("startDate")){
        criteria.and("timestamp").gte(LocalDateTime.parse(value));
        continue;
      }

      if(key.equals("endDate")){
        criteria.and("timestamp").lte(LocalDateTime.parse(value));
        continue;
      }

      if(ALLOWED_SEARCH_KEYS.contains(key)){
        criteria =  criteria.and(key).is(value);
      }

    }

    Query query = new Query(criteria);

    if(textCriteria != null){
      query.addCriteria(textCriteria);
    }

    return mongoTemplate.find(query, Log.class, "log");
  }

}
