package com.belrose.agreservice.util;

import com.belrose.agreservice.model.Agre;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public final  class TestHelper {
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public String jsonObjectAsString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public Agre getAgre() throws IOException {
        ClassPathResource resource = new ClassPathResource("/agre.json", Agre.class);
        return objectMapper.readValue(resource.getInputStream(), Agre.class);
    }
}
