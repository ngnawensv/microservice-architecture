package com.belrose.docservice.service;

import com.belrose.docservice.TestHelper;
import com.belrose.docservice.model.docs.Docs;
import com.belrose.docservice.service.impl.DocsServiceImpl;
import com.belrose.docservice.webclient.DocsServiceClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
public class DocsServiceTestV2 {
    @InjectMocks
    private DocsServiceImpl docsService;
    @Mock
    private DocsServiceClient docsServiceClient;
     TestHelper testHelper=new TestHelper();
    private final String requestId = UUID.randomUUID().toString();


    @Test
    void saveDocs_Docs_ReturnDocs() throws Exception {
        given(docsServiceClient.sendDocsToRepoWriteV1(testHelper.getDocs())).willReturn(testHelper.getDocs());

       Docs docs= docsService.sendDocsToRepoWriteV1(testHelper.getDocs());

        assertEquals(docs.getName(),testHelper.getDocs().getName());
    }
}
