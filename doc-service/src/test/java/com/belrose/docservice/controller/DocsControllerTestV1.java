package com.belrose.docservice.controller;

import com.belrose.docservice.TestHelper;
import com.belrose.docservice.constant.uri.Uri;
import com.belrose.docservice.controller.docs.DocsController;
import com.belrose.docservice.model.docs.Docs;
import com.belrose.docservice.service.DocsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

@WebFluxTest(DocsController.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // fix to execute  file as cycle
public class DocsControllerTestV1 {

    @Autowired
    WebTestClient webTestClient;
     @MockBean
    DocsService docsService;

     private final TestHelper testHelper = new TestHelper();

     public final String requestId = UUID.randomUUID().toString();
 /*   Jwt jwt= Jwt.withTokenValue("token")
            .header("xxxx","yyy")
            .claim("xxxx1","yyy1")
            .claim("xxxx2","yyy2")
            .build();
            */
    /*
@BeforeAll
public void setup(){
    webTestClient = webTestClient.mutateWith(mockJwt().jwt(jwt))
            .mutateWith(csrf());
}
*/

    @BeforeEach
    public void setup(){
        webTestClient = webTestClient.mutate()
                .responseTimeout(Duration.ofMillis(30000))
                .build();
    }

    @Test
    void saveDocs_Docs_ReturnDocs() throws IOException {
        given(docsService.sendDocsToRepoWriteV1(testHelper.getDocs())).willReturn(testHelper.getDocs());
        String  uri = Uri.DOCS + Uri.URI_DOCS_TO_REPO_WRITE_V1;
        webTestClient
                .post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(testHelper.getDocs()), Docs.class)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

    }

}

