package com.belrose.writereposervice.service.agre.impl;

import com.belrose.writereposervice.model.agre.Agre;
import com.belrose.writereposervice.model.agre.AgreEncoded;
import com.belrose.writereposervice.model.file.SaveFileDetails;
import com.belrose.writereposervice.repository.agre.AgreEncodedRepository;
import com.belrose.writereposervice.service.agre.AgreEncodedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AgreEncodedServiceImpl  implements AgreEncodedService {
    private final AgreEncodedRepository agreEncodedRepository;

    public AgreEncodedServiceImpl(AgreEncodedRepository agreEncodedRepository) {
        this.agreEncodedRepository = agreEncodedRepository;
    }

    @Override
    public Mono<AgreEncoded> saveAgreEncoded(AgreEncoded agreEncoded) {
        return agreEncodedRepository.save(agreEncoded);
    }

    @Override
    public Flux<AgreEncoded> getAllAgreEncoded() {
        return agreEncodedRepository.findAll();
    }

}
