package com.belrose.docservice.controller;

import com.belrose.docservice.TestHelper;
import com.belrose.docservice.constant.uri.Uri;
import com.belrose.docservice.service.DocsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DocsControllerTestV2 {

    @Autowired
    MockMvc mockMvc;
     @MockBean
    DocsService docsService;
     private final TestHelper testHelper = new TestHelper();
     private final String requestId = UUID.randomUUID().toString();
     @Autowired
     private WebApplicationContext webApplicationContext;

/*
    @BeforeEach
    public void initialize() throws IOException {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(new CustoUrlFilter(), "/docs")
                .build();
    }
*/

    @Test
    void saveDocs_Docs_ReturnDocs() throws Exception {
        given(docsService.sendDocsToRepoWriteV1(testHelper.getDocs())).willReturn(testHelper.getDocs());
        String  uri = Uri.DOCS + Uri.URI_DOCS_TO_REPO_WRITE_V1;
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri)
               // .with((opaqueTken().attributes(attr->attr.putAll(setAttributes()))))
                .content(testHelper.jsonObjectAsString(testHelper.getDocs()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", is("test")));

    }


    private HashMap<String,String> setAttributes(){
        HashMap<String,String> maps = new HashMap<>();
        maps.put("xxx","yyy");
        maps.put("xxx1","yyy2");
        return maps;
    }

}

