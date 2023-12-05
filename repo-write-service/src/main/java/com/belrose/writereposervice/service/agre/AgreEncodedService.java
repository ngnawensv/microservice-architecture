package com.belrose.writereposervice.service.agre;

import com.belrose.writereposervice.model.agre.Agre;
import com.belrose.writereposervice.model.agre.AgreEncoded;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AgreEncodedService {
    Mono<AgreEncoded> saveAgreEncoded(AgreEncoded agreEncoded);
    Flux<AgreEncoded> getAllAgreEncoded();
}
