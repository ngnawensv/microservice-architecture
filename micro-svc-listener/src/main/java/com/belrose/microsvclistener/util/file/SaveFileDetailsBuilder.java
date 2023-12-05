package com.belrose.microsvclistener.util.file;

import com.belrose.microsvclistener.model.agre.Address;
import com.belrose.microsvclistener.model.agre.Agre;
import com.belrose.microsvclistener.model.file.MetaDataFile;
import com.belrose.microsvclistener.model.file.SaveFileDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.OffsetDateTime;
import java.util.HexFormat;
import java.util.UUID;

@Builder
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public  final  class SaveFileDetailsBuilder {
    public  static SaveFileDetails buildSaveFileDetails(Agre agre){
        //just to help to copy a json. process: add a break point on the line 44 and run your app. Evaluate expreesion and copy value
        try{
            log.info("",new ObjectMapper().writeValueAsString(agre));
        }catch(Exception ex){
            log.error("Exception",ex);
        }
        var metaDataFile = MetaDataFile
                .builder()
                .fileName(agre.getName())
                .originalFileName(agre.getName())
                .displayName(agre.getName())
                .createdBy(!CollectionUtils.isEmpty(agre.getAddressList())?agre.getAddressList().stream().map(Address::getEmail).findFirst().orElse("none"):"")
                .createdDate(OffsetDateTime.now().toString())
                .build();
        return SaveFileDetails
                .builder()
                .fileType("Agre")
                .metaData(metaDataFile)
                .build();
    }

    public static MultiValueMap<String, Object> buildMultipartFile(byte[] file, SaveFileDetails saveFileDetails) {
        //just to help to copy a json. process: add a break point on the line 44 and run your app. Evaluate expreesion and copy value
        try{
            log.info("",new ObjectMapper().writeValueAsString(saveFileDetails));
        }catch(Exception ex){
            log.error("Exception",ex);
        }

        MultiValueMap<String, Object> multipartFile = new LinkedMultiValueMap<>();
        multipartFile.add("saveFileDetails",saveFileDetails);
        multipartFile.add("file", new ByteArrayResource(file){
            @Override
            public String getFilename(){return UUID.randomUUID().toString();
            }
        });
        return multipartFile;
    }

}
