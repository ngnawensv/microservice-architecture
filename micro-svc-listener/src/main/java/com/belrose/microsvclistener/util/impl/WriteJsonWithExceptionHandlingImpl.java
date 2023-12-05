package com.belrose.microsvclistener.util.impl;

import com.belrose.microsvclistener.util.ReadJsonWithExceptionHandling;
import com.belrose.microsvclistener.util.WriteJsonWithExceptionHandling;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WriteJsonWithExceptionHandlingImpl {
    private  final ObjectMapper objectMapper;

    @Autowired
    public WriteJsonWithExceptionHandlingImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public  <T> String writeValue(T type){
        return WriteJsonWithExceptionHandling.write(objectMapper::writeValueAsString,type);
    }

    public  <T> T  ReadValue(String content, Class<T> type){
        return ReadJsonWithExceptionHandling.read(objectMapper::readValue,content,type);
    }
}
