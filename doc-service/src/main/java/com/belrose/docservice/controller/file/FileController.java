package com.belrose.docservice.controller.file;

import com.belrose.docservice.constant.uri.Uri;
import com.belrose.docservice.model.docs.Docs;
import com.belrose.docservice.model.file.SaveFileDetails;
import com.belrose.docservice.model.file.SaveFileDetailsResponse;
import com.belrose.docservice.service.DocsService;
import com.belrose.docservice.service.FileService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(Uri.FILE)
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(path = Uri.FILE_SAVE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public SaveFileDetailsResponse saveFile(@RequestPart(name = "saveFileDetails") SaveFileDetails saveFileDetails, @RequestPart(name = "file") MultipartFile multipartFile){

        return fileService.saveFile(saveFileDetails,multipartFile);
    }

    @GetMapping(path = Uri.GET_FILE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SaveFileDetails> getAllFiles(){

        return fileService.getAllFiles();
    }
}
