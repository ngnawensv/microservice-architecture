package com.belrose.writereposervice.service.file;

import com.belrose.writereposervice.model.file.SaveFileDetails;
import com.belrose.writereposervice.model.file.SaveFileDetailsResponse;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FileService {
    Mono<SaveFileDetailsResponse> saveFile(SaveFileDetails saveFileDetails, MultipartFile multipartFile);
    Flux<SaveFileDetails> getAllFile();
}
