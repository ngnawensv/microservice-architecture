package com.belrose.reposervice.repository.impl;

import com.belrose.reposervice.model.prod.Prod;
import com.belrose.reposervice.model.prod.Prods;
import com.belrose.reposervice.repository.ProdRepositoryCusto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ProdRepositoryCustoImpl implements ProdRepositoryCusto {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public ProdRepositoryCustoImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<Prods> saveProdsCusto(String catId, Prod prod) {
            var query = new Query(Criteria.where("categoryId").is(catId));
            var update = new Update().push("prodList.$[elmStr]",prod).filterArray("elmStr.prodId",prod.getProdId());
            var option = FindAndModifyOptions.options();
            option.returnNew(true);
            return reactiveMongoTemplate.findAndModify(query,update,option, Prods.class);
    }
}
