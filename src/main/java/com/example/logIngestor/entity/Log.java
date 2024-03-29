package com.example.logIngestor.entity;

import com.example.logIngestor.model.LogLevel;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Log implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private String id;

  @Indexed
  @TextIndexed
  private LogLevel level;

  @TextIndexed
  private String message;

  private String resourceId;

  private LocalDateTime timestamp;

  private String traceId;

  private String spanId;

  private String commit;

  private Map<String,Object> metadata;

  @CreatedDate
  private LocalDateTime createdAt;


}
