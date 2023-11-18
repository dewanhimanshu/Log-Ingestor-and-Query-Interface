package com.example.logIngestor.service;

import com.example.logIngestor.dao.LogDao;
import com.example.logIngestor.kafka.KafkaProducer;
import com.example.logIngestor.entity.Log;
import com.example.logIngestor.model.LogSearchResponseDto;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogService {

  private final KafkaProducer kafkaProducer;

  private final LogDao logDao;

  public void addLogToKafka(Log logMessage){
    log.info("log received : {} ",logMessage);

    kafkaProducer.sendLogToKafkaTopic(logMessage);
  }

  public LogSearchResponseDto searchLogsInMongo(Map<String,String> queryParams){
    return new LogSearchResponseDto(logDao.searchLogs(queryParams));
  }
}
