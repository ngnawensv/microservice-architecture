package com.belrose.writereposervice.service.docs;

import com.belrose.writereposervice.model.docs.Docs;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DocsService {
    Mono<Docs> saveDocs(Docs docs);
    Flux<Docs> getAllDocs();
}
