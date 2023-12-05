package com.belrose.docservice.service;

import com.belrose.docservice.model.docs.Docs;

public interface DocsService {
    Docs sendDocsToRepoWriteV1(Docs docs);
    Docs sendDocsToRepoWriteV2(Docs docs);
    //Docs sendDocsToRepoWriteV3(Docs docs);
}
