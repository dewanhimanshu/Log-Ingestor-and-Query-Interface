package com.example.logIngestor.entity;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Role {

  private static final long serialVersionUID = 1L;

  @Id
  String id;

  @Indexed(unique = true)
  String userId;

  List<String> allowedAccessFields;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

}
