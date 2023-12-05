package com.belrose.writereposervice.service.file.impl;

import com.belrose.writereposervice.model.file.MetaDataFile;
import com.belrose.writereposervice.model.file.SaveFileDetails;
import com.belrose.writereposervice.model.file.SaveFileDetailsResponse;
import com.belrose.writereposervice.repository.file.FileRepository;
import com.belrose.writereposervice.service.file.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    @Override
    public Mono<SaveFileDetailsResponse> saveFile(SaveFileDetails saveFileDetails, MultipartFile multipartFile) {
        log.info("Inside the saveFile: saveFileDetails {}",saveFileDetails);
        saveFileDetails.getMetaData().setCreatedDate(OffsetDateTime.now().toString());
      return   fileRepository.save(saveFileDetails).map(sfd -> SaveFileDetailsResponse.builder()
              .message("SUCCESS")
              .description(String.format("%s is successfully saved ",sfd.getMetaData().getFileName()))
              .build());
    }

    @Override
    public Flux<SaveFileDetails> getAllFile() {
        return fileRepository.findAll();
    }


}
