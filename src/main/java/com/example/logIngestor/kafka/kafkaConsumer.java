package com.example.logIngestor.kafka;

import com.example.logIngestor.config.KafkaConfig;
import com.example.logIngestor.entity.ElasticLog;
import com.example.logIngestor.entity.Log;
import com.example.logIngestor.repository.ElasticLogRepository;
import com.example.logIngestor.repository.LogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class kafkaConsumer {

  private final LogRepository logRepository;

  private final ElasticLogRepository elasticLogRepository;

  private final ObjectMapper objectMapper;

  @KafkaListener(topics = "logProcess", groupId = "my-group")
  public void kafkaLogProcessListener(Log logMessage){
    log.info("Kafka Listener {}",logMessage);

    logRepository.save(logMessage);
    elasticLogRepository.save(objectMapper.convertValue(logMessage, ElasticLog.class));
  }

}
