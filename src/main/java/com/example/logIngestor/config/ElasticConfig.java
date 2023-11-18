package com.example.logIngestor.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;


@RequiredArgsConstructor
public class ElasticConfig extends ElasticsearchConfiguration {

  @Value("${community.elasticsearch.host}")
  private final String elasticHost;

  @Override
  public ClientConfiguration clientConfiguration() {
    return ClientConfiguration.builder().connectedTo(elasticHost).build();
  }
}
