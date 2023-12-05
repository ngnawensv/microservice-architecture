package com.belrose.reposervice.repository;

import com.belrose.reposervice.model.agre.Agre;
import com.belrose.reposervice.model.prod.Prod;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdRepository extends ReactiveMongoRepository<Prod, String> {
}
