package com.example.logIngestor.model;

import com.example.logIngestor.entity.Log;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LogSearchResponseDto {

  private List<Log> logs;

}
