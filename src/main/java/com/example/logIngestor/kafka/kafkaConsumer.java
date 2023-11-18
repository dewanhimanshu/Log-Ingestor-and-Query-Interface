package com.example.logIngestor.kafka;

import com.example.logIngestor.config.KafkaConfig;
import com.example.logIngestor.model.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class kafkaConsumer {

  private final KafkaConfig kafkaConfig;

  @KafkaListener(topics = "logProcess", groupId = "my-group")
  public void kafkaLogProcessListener(Log logMessage){
    log.info("Kafka Listener {}",logMessage);
  }

}
