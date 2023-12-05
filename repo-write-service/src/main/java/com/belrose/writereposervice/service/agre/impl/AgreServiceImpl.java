package com.belrose.writereposervice.service.agre.impl;

import com.belrose.writereposervice.model.agre.Agre;
import com.belrose.writereposervice.model.agre.AgreResponse;
import com.belrose.writereposervice.repository.agre.AgreRepository;
import com.belrose.writereposervice.service.agre.AgreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AgreServiceImpl  implements AgreService {
    private final AgreRepository agreRepository;

    public AgreServiceImpl(AgreRepository agreRepository) {
        this.agreRepository = agreRepository;
    }

    @Override
    public Mono<Agre> saveAgre(Agre agre) {
        return agreRepository.save(agre);
    }
    @Override
    public Flux<Agre> getAllAgre() {
        return agreRepository.findAll();
    }

    @Override
    public Flux<Agre> getAllAgreFiltered() {
        Map<String,Agre> stringAgreMap = new HashMap<>();
        return agreRepository.findAll().flatMap(agre -> {
           /* var type = agre.getType();
            if(StringUtils.isNotEmpty(type)&&!StringUtils.equals(type,"numeric")){
                stringAgreMap.put(type,agre);
            }
            return new ArrayList<>((Collection) stringAgreMap.values().stream().toList());*/
            return null;
        });
    }
}
