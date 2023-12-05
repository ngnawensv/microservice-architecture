package com.belrose.docservice.webclient;

import com.belrose.docservice.model.file.SaveFileDetails;
import com.belrose.docservice.model.file.SaveFileDetailsResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileServiceClient {
    SaveFileDetailsResponse saveFile(SaveFileDetails saveFileDetails, MultipartFile multipartFile);

    List<SaveFileDetails> getAllFiles();
}
