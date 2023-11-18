package com.example.logIngestor.repository;

import com.example.logIngestor.entity.ElasticLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticLogRepository extends ElasticsearchRepository<ElasticLog,String> {

}
