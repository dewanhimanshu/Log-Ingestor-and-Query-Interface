package com.example.logIngestor.entity;

import com.example.logIngestor.model.LogLevel;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;


@Document(indexName = "log_index")
@Data
public class ElasticLog {


  private String id;

  private LogLevel level;

  private String message;

  private String resourceId;

  private LocalDateTime timestamp;

  private String traceId;

  private String spanId;

  private String commit;

  private Map<String,Object> metadata;

  private LocalDateTime createdAt;

}
