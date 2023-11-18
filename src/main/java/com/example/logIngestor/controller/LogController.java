package com.example.logIngestor.controller;

import com.example.logIngestor.entity.Log;
import com.example.logIngestor.model.LogSearchResponseDto;
import com.example.logIngestor.service.LogService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LogController {

  private final LogService logService;

  @PostMapping("/log")
  public ResponseEntity<Void> addLog(
      @RequestBody Log log
  ) {
    logService.addLogToKafka(log);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/log/search")
  public ResponseEntity<LogSearchResponseDto> searchLogFromDb(
  @RequestParam Map<String,String> queryParams,
      @RequestHeader(defaultValue = "0") String userId
  ){
     if(userId.equals("0")){
       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
     }

      return ResponseEntity.ok().body(logService.searchLogsInMongo(queryParams,userId));

  }

}
