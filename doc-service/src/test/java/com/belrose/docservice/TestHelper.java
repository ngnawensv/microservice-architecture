package com.belrose.docservice;

import com.belrose.docservice.model.docs.Docs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.drools.io.ClassPathResource;

import java.io.IOException;

public class TestHelper {
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

    public Docs getDocs() throws IOException {
        ClassPathResource resource = new ClassPathResource("/mock-data/docs.json", Docs.class);
        return objectMapper.readValue(resource.getInputStream(), Docs.class);
    }

    public Docs getDocsV1() throws IOException {
        return objectMapper.readValue(new ClassPathResource("/mock-data/docs.json", Docs.class).getInputStream(), Docs.class);
    }

    public  String jsonObjectAsString(Object object) throws JsonProcessingException{
        return objectMapper.writeValueAsString(object);
    }
}
