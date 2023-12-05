package com.belrose.reposervice.repository;

import com.belrose.reposervice.model.agre.Agre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AgreRepository extends ReactiveMongoRepository<Agre, String> {

}
