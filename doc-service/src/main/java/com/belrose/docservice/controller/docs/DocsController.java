package com.belrose.docservice.controller.docs;

import com.belrose.docservice.constant.uri.Uri;
import com.belrose.docservice.model.CheckRequest;
import com.belrose.docservice.model.docs.Docs;
import com.belrose.docservice.service.AgreEncodedCheckService;
import com.belrose.docservice.service.DocsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping(Uri.DOCS)
@Slf4j
public class DocsController {
    private final DocsService docsService;
    private final AgreEncodedCheckService agreEncodedCheckService;

    public DocsController(DocsService docsService, AgreEncodedCheckService agreEncodedCheckService) {
        this.docsService = docsService;
        this.agreEncodedCheckService = agreEncodedCheckService;
    }

    @PostMapping(path = Uri.URI_DOCS_TO_REPO_WRITE_V1, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Docs saveDocsV1(@RequestBody Docs docs){

        //AOP code added to check if request is valid
        var checkRequest = CheckRequest.builder()
                .checkingElement(docs.getName())
                .build();
        agreEncodedCheckService.checkAgreEncoded(Collections.singletonList(checkRequest));

        return docsService.sendDocsToRepoWriteV1(docs);
    }

    @PostMapping(path = Uri.URI_DOCS_TO_REPO_WRITE_V2, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Docs saveDocsV2(@RequestBody Docs docs){
        return docsService.sendDocsToRepoWriteV2(docs);
    }
}
