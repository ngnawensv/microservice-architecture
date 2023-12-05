package com.belrose.microsvclistener.service;

import com.belrose.microsvclistener.model.TriggerEvent;
import com.belrose.microsvclistener.model.agre.Agre;
import reactor.core.publisher.Mono;

public interface AgreService {
   // String docsWithFileToSave(Agre agre,byte[] file);
    Mono<Agre> updateAgre(TriggerEvent triggerEvent);
}
