package com.belrose.microsvclistener.repository;

import com.belrose.microsvclistener.model.agre.Agre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AgreRepository extends ReactiveMongoRepository<Agre,String>,AgreCustoRepository {
   // Mono<Agre> findByAgreTransaction(String agreTransaction);


}
