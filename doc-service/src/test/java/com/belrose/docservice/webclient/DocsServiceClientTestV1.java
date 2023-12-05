package com.belrose.docservice.webclient;

import com.belrose.docservice.TestHelper;
import com.belrose.docservice.config.AppConfig;
import com.belrose.docservice.exception.RepoWriteServiceException;
import com.belrose.docservice.model.docs.Docs;
import com.belrose.docservice.webclient.impl.DocsServiceClientImpl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class DocsServiceClientTestV1 {
    @Autowired
    private AppConfig appConfig;
    private  static MockWebServer mockWebServer;
    private DocsServiceClient docsServiceClient;
    TestHelper testHelper=new TestHelper();
    private final String requestId = UUID.randomUUID().toString();

    @BeforeEach
    public void initialize(@Value("${app.port}") int port) throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(port);
        String baseUrl = String.format("http://localhost:%s",port);
        docsServiceClient = new DocsServiceClientImpl(baseUrl,appConfig);
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void saveDocs_Docs_ReturnDocs() throws Exception {
        MockResponse mockResponse = new MockResponse()
                .setBody(testHelper.jsonObjectAsString(testHelper.getDocs()))
                .setResponseCode(200)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        mockWebServer.enqueue(mockResponse);

         Docs docs =docsServiceClient.sendDocsToRepoWriteV1(testHelper.getDocs());
        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        assertNotNull(docs);
        assertNotNull(docs.getName());
        assertEquals(appConfig.getSendToRepo(),recordedRequest.getPath());
        assertEquals("POST",recordedRequest.getMethod());
    }

    @ParameterizedTest
    @ValueSource(ints = {400,403,408,500})
    void saveDocs_Docs_fail(int status){
        MockResponse mockResponse = new MockResponse()
                .setBody("")
                .setResponseCode(status)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockWebServer.enqueue(mockResponse);

        assertThrowsExactly(RepoWriteServiceException.class, ()->docsServiceClient.sendDocsToRepoWriteV1(testHelper.getDocs()));
    }
}
