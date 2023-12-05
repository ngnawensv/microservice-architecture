package com.belrose.agreservice.service;

import com.belrose.agreservice.model.AgreEncoded;
import com.belrose.agreservice.service.impl.AgreServiceImpl;
import com.belrose.agreservice.util.TestHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.io.IOException;

import static com.belrose.agreservice.constants.AppConstants.AGRE_SERVICE_CIRCUIT_BREAKER;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ActiveProfiles("test")
@Execution(ExecutionMode.SAME_THREAD)
public class CircuitBreakerTest {

    private AgreService agreService;
    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    private static MockWebServer mockWebServer;
    private final TestHelper testHelper= new TestHelper();

    @BeforeEach
    public void setUp()  {
        circuitBreakerRegistry.circuitBreaker(AGRE_SERVICE_CIRCUIT_BREAKER).reset();
        mockWebServer = new MockWebServer();
        String baseUrl = String.format("http://localhost:%s",mockWebServer.getPort());
        agreService = new AgreServiceImpl(baseUrl);
    }

    @AfterEach
    public  void tearDown()  throws  IOException{
        mockWebServer.shutdown();
    }

    @Test
    public void saveAgreAndAgreEncodedExceptionWhenCircuitBreakerIsOpen() throws IOException {
        circuitBreakerRegistry.circuitBreaker(AGRE_SERVICE_CIRCUIT_BREAKER).transitionToOpenState();
        MockResponse mockResponse = new MockResponse()
                .setBody(testHelper.jsonObjectAsString(testHelper.getAgre()))
                .setResponseCode(200)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        mockWebServer.enqueue(mockResponse);

        MockResponse mockResponse1 = new MockResponse()
                .setBody(testHelper.jsonObjectAsString(AgreEncoded.builder().build()))
                .setResponseCode(200)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        mockWebServer.enqueue(mockResponse1);
        try{
            agreService.saveAgreAndAgreEncoded(testHelper.getAgre());
            Assertions.fail("RuntimeException expected");
        }catch (Exception exception){
            assertEquals(CallNotPermittedException.class, exception.getClass());
        }
    }

    @Test
    public void saveAgreAndAgreEncodedExceptionWhenCircuitBreakerIsClose() throws IOException {
        circuitBreakerRegistry.circuitBreaker(AGRE_SERVICE_CIRCUIT_BREAKER).transitionToClosedState();
        MockResponse mockResponse = new MockResponse()
                .setBody(testHelper.jsonObjectAsString(testHelper.getAgre()))
                .setResponseCode(200)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        mockWebServer.enqueue(mockResponse);

        MockResponse mockResponse1 = new MockResponse()
                .setBody(testHelper.jsonObjectAsString(AgreEncoded.builder().build()))
                .setResponseCode(200)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        mockWebServer.enqueue(mockResponse1);

       var resp = agreService.saveAgreAndAgreEncoded(testHelper.getAgre());

       assertNotNull(resp);

    }
}
