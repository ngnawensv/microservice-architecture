package com.belrose.microsvclistener.service;

import com.belrose.microsvclistener.model.TriggerEvent;
import com.belrose.microsvclistener.model.actlog.ActLog;
import com.belrose.microsvclistener.repository.ActLogRepository;
import com.belrose.microsvclistener.repository.AgreRepository;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import com.belrose.microsvclistener.util.TestHelper;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest()
@ActiveProfiles("test")
public class TriggerEventAsyncServiceTest {
    private TriggerEventAsyncService triggerEventAsyncService;
    private static MockWebServer mockWebServer;
    @MockBean
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @MockBean
    private AgreService agreService;
    @MockBean
    private AgreRepository agreRepository;
    @MockBean
    private ActLogRepository actLogRepository;
    private TestHelper testHelper = new TestHelper();

    private String requestId = UUID.randomUUID().toString();

    @BeforeEach
    void initialize() throws IOException{
        mockWebServer = new MockWebServer();
        String baseUrl= String.format("http://localhost:%s",mockWebServer.getPort());
        threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.initialize();
        triggerEventAsyncService = new TriggerEventAsyncService(agreService,actLogRepository);
    }

    @AfterEach
    void tearDown() throws IOException{
        mockWebServer.shutdown();
    }

    @Test
    void handleEvents_TriggerEvent_thenReturnVoid() throws IOException, InterruptedException {
        var triggerEvent = TriggerEvent.builder().eventName("test").agreTransaction("test").build();
        when(agreRepository.updateAgre(triggerEvent)).thenReturn( Mono.just(testHelper.getAgre()));
        when(agreService.updateAgre(triggerEvent)).thenReturn( Mono.just(testHelper.getAgre()));
        when(actLogRepository.save(any(ActLog.class))).thenReturn( Mono.empty());

        triggerEventAsyncService.handleEvents(triggerEvent);

        boolean awaitTermination = threadPoolTaskExecutor.getThreadPoolExecutor().awaitTermination(1, TimeUnit.SECONDS);
        assertThat(String.valueOf(awaitTermination),true);
    }
}
