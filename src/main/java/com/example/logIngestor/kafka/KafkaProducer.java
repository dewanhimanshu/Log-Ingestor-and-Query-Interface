package com.example.logIngestor.kafka;

import com.example.logIngestor.config.KafkaConfig;
import com.example.logIngestor.entity.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

  private final KafkaTemplate<String, Log> logKafkaTemplate;

  private final KafkaConfig kafkaConfig;

  public void sendLogToKafkaTopic(Log log){
    logKafkaTemplate.send(kafkaConfig.getLogProcessKafkaTopic(),log);
  }
}
