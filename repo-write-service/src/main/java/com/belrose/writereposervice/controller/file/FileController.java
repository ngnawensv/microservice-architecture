package com.belrose.writereposervice.controller.file;

import com.belrose.writereposervice.constant.uri.Uri;
import com.belrose.writereposervice.model.file.SaveFileDetails;
import com.belrose.writereposervice.model.file.SaveFileDetailsResponse;
import com.belrose.writereposervice.service.file.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Uri.FILE)
@Slf4j
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(path = Uri.FILE_SAVE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<SaveFileDetailsResponse> saveFile(@RequestPart(name = "saveFileDetails")  SaveFileDetails saveFileDetails, @RequestPart(name = "file") MultipartFile multipartFile){
        log.info("Controller->saveFile: saveFileDetails {}",saveFileDetails);
        return fileService.saveFile(saveFileDetails,multipartFile);
    }

    @GetMapping(path = Uri.GET_FILE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<SaveFileDetails> getAllFiles(){
        return fileService.getAllFile();
    }
}
