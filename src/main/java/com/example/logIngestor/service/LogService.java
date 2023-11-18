package com.example.logIngestor.service;

import com.example.logIngestor.kafka.KafkaProducer;
import com.example.logIngestor.model.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogService {

  private final KafkaProducer kafkaProducer;

  public void addLogToKafka(Log logMessage){
    log.info("log received : {} ",logMessage);

    kafkaProducer.sendLogToKafkaTopic(logMessage);
  }
}
