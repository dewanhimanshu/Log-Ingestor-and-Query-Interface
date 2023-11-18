package com.example.logIngestor.dao;

import com.example.logIngestor.entity.Log;
import java.time.LocalDateTime;
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

  private final static String TIMESTAMP = "TIMESTAMP";

  public List<Log> searchLogs(Map<String,String> searchCriteria,List<String> allowedSearchKeys,String startDate,String endDate){
    TextCriteria textCriteria = null;
    Criteria criteria = new Criteria();

    //add date filters
    if(startDate != null && endDate != null){
      criteria.and(TIMESTAMP).gte(LocalDateTime.parse(startDate))
          .lte(LocalDateTime.parse(endDate));
    }else if(startDate != null){
      criteria.and(TIMESTAMP).gte(LocalDateTime.parse(startDate));
    }else if(endDate != null){
      criteria.and(TIMESTAMP).lte(LocalDateTime.parse(endDate));
    }


    for(Map.Entry<String,String> entry : searchCriteria.entrySet()){
      String key = entry.getKey();
      String value = entry.getValue();

      //filter for full text searching
      if(key.equals("text")){
        textCriteria = TextCriteria.forDefaultLanguage().matchingAny(value);
        continue;
      }

      if(key.equals("startDate") || key.equals("endDate")){
        continue;
      }

      //add filters based on allowed access
      if(allowedSearchKeys.contains(key)){
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
