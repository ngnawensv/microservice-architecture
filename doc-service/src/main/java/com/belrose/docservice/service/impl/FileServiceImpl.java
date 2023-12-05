package com.belrose.docservice.service.impl;

import com.belrose.docservice.model.file.SaveFileDetails;
import com.belrose.docservice.model.file.SaveFileDetailsResponse;
import com.belrose.docservice.service.FileService;
import com.belrose.docservice.webclient.FileServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private final FileServiceClient fileServiceClient;

    public FileServiceImpl(FileServiceClient fileServiceClient) {
        this.fileServiceClient = fileServiceClient;
    }

    @Override
    public SaveFileDetailsResponse saveFile(SaveFileDetails saveFileDetails, MultipartFile multipartFile) {
      return   fileServiceClient.saveFile(saveFileDetails,multipartFile);
    }

    @Override
    public List<SaveFileDetails> getAllFiles() {
        return fileServiceClient.getAllFiles();
    }
}
