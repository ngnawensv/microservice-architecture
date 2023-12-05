package com.belrose.reposervice.repository;

import com.belrose.reposervice.model.prod.Prod;
import com.belrose.reposervice.model.prod.Prods;
import reactor.core.publisher.Mono;

public interface ProdRepositoryCusto{
    Mono<Prods> saveProdsCusto(String catId, Prod prod );
}
