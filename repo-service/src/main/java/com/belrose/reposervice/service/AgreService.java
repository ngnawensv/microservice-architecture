package com.belrose.reposervice.service;


import com.belrose.reposervice.model.agre.Agre;
import reactor.core.publisher.Mono;

public interface AgreService {
    Mono<Agre> saveAgre(Agre agre);
}
