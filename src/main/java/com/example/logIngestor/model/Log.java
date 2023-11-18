package com.example.logIngestor.model;

import java.io.Serializable;
import java.util.Map;
import lombok.Data;

@Data
public class Log implements Serializable {

  private static final long serialVersionUID = 1L;

  private LogLevel level;

  private String message;

  private String resourceId;

  private String timestamp;

  private String traceId;

  private String spanId;

  private String commit;

  private Map<String,Object> metadata;

}
