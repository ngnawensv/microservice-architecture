package com.belrose.docservice.webclient;

import com.belrose.docservice.model.docs.Docs;
public interface DocsServiceClient {
     Docs sendDocsToRepoWriteV1(Docs docs);
     Docs sendDocsToRepoWriteV2(Docs docs);
     //Docs sendDocsToRepoWriteV3(Docs docs);
}
