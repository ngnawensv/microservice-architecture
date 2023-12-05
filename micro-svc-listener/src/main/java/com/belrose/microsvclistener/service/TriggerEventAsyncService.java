package com.belrose.microsvclistener.service;

import com.belrose.microsvclistener.config.AppConfig;
import com.belrose.microsvclistener.model.TriggerEvent;
import com.belrose.microsvclistener.model.actlog.ActLog;
import com.belrose.microsvclistener.model.agre.Agre;
import com.belrose.microsvclistener.exception.MicroSvcListenerException;
import com.belrose.microsvclistener.repository.ActLogRepository;
import com.belrose.microsvclistener.util.impl.WriteJsonWithExceptionHandlingImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeFormatterBuilder;
import java.util.HexFormat;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class TriggerEventAsyncService {

  private AgreService agreService;
  private  WriteJsonWithExceptionHandlingImpl writeJsonWithExceptionHandling;
  private ActLogRepository actLogRepository;
  public ObjectMapper objectMapper;
  @Autowired
  public TriggerEventAsyncService(AgreService agreService, WriteJsonWithExceptionHandlingImpl writeJsonWithExceptionHandling, ActLogRepository actLogRepository) {
    this.agreService = agreService;
    this.writeJsonWithExceptionHandling = writeJsonWithExceptionHandling;
    this.actLogRepository = actLogRepository;
  }

 //Used for unit test
  public TriggerEventAsyncService(AgreService agreService , ActLogRepository actLogRepository) {
    this.agreService = agreService;
    this.actLogRepository = actLogRepository;
    this.objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;
    this.writeJsonWithExceptionHandling = new WriteJsonWithExceptionHandlingImpl(this.objectMapper);
  }

  @Async("asyncExecutor")
  public CompletableFuture<String> handleEvents(TriggerEvent triggerEvent) throws JsonProcessingException {
    String response = null;
    if(triggerEvent !=null && triggerEvent.getAgreTransaction()!=null){
      var agre = agreService.updateAgre(triggerEvent).block();
      log.info("TriggerEventAsyncService->handleEvents : Agre {} :",agre);
      if(agre==null){
        throw  new MicroSvcListenerException("Error: Agre is null");
      }
    //  response = agreService.docsWithFileToSave(agre,"Test".getBytes());
      eventSave( agre, triggerEvent);
      response = "SUCCESS";
    }
    log.info("TriggerEventAsyncService->Inside Async method, Treatment according the trigger event {} :",triggerEvent);
    return CompletableFuture.completedFuture(response);
  }

  public  void eventSave(Agre agre,TriggerEvent triggerEvent){
triggerEvent.setTimestamp(System.currentTimeMillis()/1000);
    String date = new DateTimeFormatterBuilder().appendInstant(3).toFormatter().format(Instant.ofEpochSecond(triggerEvent.getTimestamp()));
     ActLog actLog = ActLog.builder()
             .eventId(agre.getName())
             .action(agre.getName())
             .module("Agre")
             .date(date)
             .data("Agre:/n" +writeJsonWithExceptionHandling.writeValue(agre)+"/nTriggerEvent: /n " + writeJsonWithExceptionHandling.writeValue(triggerEvent) )
             .user("SVN")
             .build();
     actLogRepository.save(actLog).block();
     log.info("TriggerEventAsyncService->eventSave : actLog {}",actLog);
  }

}
