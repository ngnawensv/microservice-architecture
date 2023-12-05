package com.belrose.writereposervice.service.docs.impl;

import com.belrose.writereposervice.model.docs.Docs;
import com.belrose.writereposervice.repository.docs.DocsRepository;
import com.belrose.writereposervice.service.docs.DocsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.time.OffsetTime;

@Service
@Slf4j
public class DocsServiceImpl implements DocsService {

    private final DocsRepository docsRepository;

    public DocsServiceImpl(DocsRepository docsRepository) {
        this.docsRepository = docsRepository;
    }

    @Override
    public Mono<Docs> saveDocs(Docs docs) {
        docs.setCreationDate(OffsetDateTime.now().toString());
        return docsRepository.save(docs);
    }

    @Override
    public Flux<Docs> getAllDocs() {
        return docsRepository.findAll();
    }
}
