package com.belrose.writereposervice.service;

import com.belrose.writereposervice.controller.docs.DocsController;
import com.belrose.writereposervice.model.docs.Docs;
import com.belrose.writereposervice.repository.docs.DocsRepository;
import com.belrose.writereposervice.service.docs.DocsService;
import com.belrose.writereposervice.service.docs.impl.DocsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class DocsServiceTest {
    @InjectMocks
    private DocsServiceImpl docsService;
    @Mock
    private DocsRepository docsRepository;

    private  final Docs docs=Docs.builder().name("test").build();

    @BeforeEach
    public void setUp(){
        BDDMockito.when(docsRepository.save(docs)).thenReturn(Mono.just(docs));
        BDDMockito.when(docsRepository.findAll()).thenReturn(Flux.just(docs));
    }

    @Test
    @DisplayName("Save Docs")
    void save_docs(){
        StepVerifier.create(docsService.saveDocs(docs))
                .expectSubscription()
                .expectNext(docs)
                .verifyComplete();
    }

    @Test
    @DisplayName("Get all Docs")
    void get_all_docs(){
        StepVerifier.create(docsService.getAllDocs())
                .expectSubscription()
                .expectNext(docs)
                .verifyComplete();
    }
}
