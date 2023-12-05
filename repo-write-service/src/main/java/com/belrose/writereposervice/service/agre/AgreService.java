package com.belrose.writereposervice.service.agre;

import com.belrose.writereposervice.model.agre.Agre;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AgreService {
    Mono<Agre> saveAgre(Agre agre);
    Flux<Agre> getAllAgre();
    Flux<Agre> getAllAgreFiltered();
}
