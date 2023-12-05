package com.belrose.writereposervice.repository.docs;

import com.belrose.writereposervice.model.docs.Docs;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DocsRepository extends ReactiveMongoRepository<Docs,String> {
}
