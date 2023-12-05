package com.belrose.writereposervice.repository.agre;

import com.belrose.writereposervice.model.agre.Agre;
import com.belrose.writereposervice.model.docs.Docs;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AgreRepository extends ReactiveMongoRepository<Agre,String> {

}
