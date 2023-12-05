package com.belrose.docservice.service;

import com.belrose.docservice.model.file.SaveFileDetails;
import com.belrose.docservice.model.file.SaveFileDetailsResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService  {
   SaveFileDetailsResponse saveFile(SaveFileDetails saveFileDetails, MultipartFile multipartFile) ;

   List<SaveFileDetails> getAllFiles();
}
