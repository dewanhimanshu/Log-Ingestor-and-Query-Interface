package com.example.logIngestor.controller;

import com.example.logIngestor.model.Log;
import com.example.logIngestor.service.LogService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
