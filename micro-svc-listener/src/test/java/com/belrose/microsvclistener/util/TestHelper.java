package com.belrose.microsvclistener.util;

import com.belrose.microsvclistener.model.actlog.ActLog;
import com.belrose.microsvclistener.model.agre.Agre;
import com.belrose.microsvclistener.model.file.SaveFileDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public final  class TestHelper {
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    public String jsonObjectAsString(Object object) throws JsonProcessingException{
        return objectMapper.writeValueAsString(object);
    }

    public Agre getAgre() throws IOException{
        ClassPathResource resource = new ClassPathResource("/agre.json", Agre.class);
        return objectMapper.readValue(resource.getInputStream(), Agre.class);
    }

    public ActLog getActLog() throws IOException{
        ClassPathResource resource = new ClassPathResource("/actLog.json", ActLog.class);
        return objectMapper.readValue(resource.getInputStream(), ActLog.class);
    }

    public SaveFileDetails getSaveFileDetails() throws IOException{
        ClassPathResource resource = new ClassPathResource("/saveFileDetails.json", SaveFileDetails.class);
        return objectMapper.readValue(resource.getInputStream(), SaveFileDetails.class);
    }
}
