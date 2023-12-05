package com.belrose.writereposervice.repository.file;

import com.belrose.writereposervice.model.docs.Docs;
import com.belrose.writereposervice.model.file.SaveFileDetails;
import com.belrose.writereposervice.model.file.SaveFileDetailsResponse;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;


public interface FileRepository extends ReactiveMongoRepository<SaveFileDetails,String> {
}
