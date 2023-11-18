package com.example.logIngestor.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic logProcessTopic(){
      return TopicBuilder.name("logProcess").build();
    }

    public String getLogProcessKafkaTopic(){
      return "logProcess";
    }
}
