package com.belrose.writereposervice.controller.docs;

import com.belrose.writereposervice.constant.uri.Uri;
import com.belrose.writereposervice.model.docs.Docs;
import com.belrose.writereposervice.service.docs.DocsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Uri.DOCS)
public class DocsController {
    private final DocsService docsService;

    public DocsController(DocsService docsService) {
        this.docsService = docsService;
    }

    @PostMapping(path = Uri.POST_DOCS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Docs> saveDocs(@RequestBody Docs docs){
        return docsService.saveDocs(docs);
    }
    @GetMapping(path = Uri.GET_DOCS, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Docs> getAllDocs(){
        return docsService.getAllDocs();
    }
}
