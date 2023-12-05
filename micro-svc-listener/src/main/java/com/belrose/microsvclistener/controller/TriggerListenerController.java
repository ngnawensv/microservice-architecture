package com.belrose.microsvclistener.controller;

import com.belrose.microsvclistener.model.TriggerEvent;
import com.belrose.microsvclistener.service.TriggerEventAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
@RequestMapping("/listener")
public class TriggerListenerController {
  private  final TriggerEventAsyncService asyncService;

  public TriggerListenerController(TriggerEventAsyncService asyncService) {
    this.asyncService = asyncService;
  }


  @PostMapping(path = "/event")
  public  ResponseEntity<CompletableFuture<String>>  eventListener(@RequestBody TriggerEvent triggerEvent){
    log.info("TriggerListenerController->TriggerEvent : {} ",triggerEvent);
    CompletableFuture<String> response;
    try {
      response =  asyncService.handleEvents(triggerEvent);
    }catch (Exception ex){
      log.error("TriggerListenerController->TriggerEvent : {}", ExceptionUtils.getRootCauseMessage(ex));
      throw new RuntimeException(ex.getMessage());
    }
    return ResponseEntity.ok(response);
  }
}
