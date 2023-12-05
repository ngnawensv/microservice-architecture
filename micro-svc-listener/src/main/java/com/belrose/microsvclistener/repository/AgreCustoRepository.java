package com.belrose.microsvclistener.repository;

import com.belrose.microsvclistener.model.TriggerEvent;
import com.belrose.microsvclistener.model.agre.Agre;
import reactor.core.publisher.Mono;

public interface AgreCustoRepository {
    Mono<Agre> updateAgre(TriggerEvent triggerEvent);
}