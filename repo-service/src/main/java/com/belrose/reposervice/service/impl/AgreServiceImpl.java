package com.belrose.reposervice.service.impl;

import com.belrose.reposervice.model.agre.Agre;
import com.belrose.reposervice.repository.AgreRepository;
import com.belrose.reposervice.service.AgreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class AgreServiceImpl implements AgreService {

    private final AgreRepository agreRepository;

    public AgreServiceImpl(AgreRepository agreRepository) {
        this.agreRepository = agreRepository;
    }

    @Override
    public Mono<Agre> saveAgre(Agre agre) {
        return agreRepository.save(agre);
    }
}
