package com.belrose.docservice.service.impl;

import com.belrose.docservice.model.docs.Docs;
import com.belrose.docservice.service.DocsService;
import com.belrose.docservice.webclient.DocsServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DocsServiceImpl implements DocsService {

    private final DocsServiceClient docsServiceClient;

    public DocsServiceImpl(DocsServiceClient docsServiceClient) {
        this.docsServiceClient = docsServiceClient;
    }


    @Override
    public Docs sendDocsToRepoWriteV1(Docs docs) {
        return docsServiceClient.sendDocsToRepoWriteV1(docs);
    }

    @Override
    public Docs sendDocsToRepoWriteV2(Docs docs) {
        return docsServiceClient.sendDocsToRepoWriteV2(docs);
    }
}
