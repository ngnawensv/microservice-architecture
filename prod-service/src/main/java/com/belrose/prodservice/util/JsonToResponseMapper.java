package com.belrose.prodservice.util;

import com.belrose.prodservice.exception.ProdServiceException;
import com.belrose.prodservice.model.prods.ProdResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonToResponseMapper {

    public static ProdResponse saveProduct(final String filePath){
        return getResponseFromFile(filePath, ProdResponse.class,"save product");
    }

    public static <T> T getResponseFromFile(final String filePath, Class<T> responseType, String classType){
        var mapper= new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        T response;
        try(var inputStream =new ClassPathResource(filePath).getInputStream()){
            response=mapper.readValue(inputStream,responseType);
        }catch (IOException ex){
            var errorMsg=String.format("Error reading %s response file",classType);
            log.error(errorMsg+" at path "+filePath,ex);
            throw new ProdServiceException(errorMsg);
        }
        return response;
    }
}
