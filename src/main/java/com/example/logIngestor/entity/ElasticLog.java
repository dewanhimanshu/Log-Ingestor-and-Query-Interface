package com.example.logIngestor.entity;

import com.example.logIngestor.model.LogLevel;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;

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
