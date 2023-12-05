package com.belrose.microsvclistener.repository.impl;

import com.belrose.microsvclistener.model.TriggerEvent;
import com.belrose.microsvclistener.model.agre.Agre;
import com.belrose.microsvclistener.repository.AgreCustoRepository;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;

public class AgreCustoRepositoryImpl implements AgreCustoRepository {
    private  final ReactiveMongoTemplate mongoTemplate;

    public AgreCustoRepositoryImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<Agre> updateAgre(TriggerEvent triggerEvent) {
       var query = new Query(Criteria.where("actionDetails.agreTransaction").is(triggerEvent.getAgreTransaction()));
       var update = new Update().push("triggerEvent",triggerEvent);
       var option = FindAndModifyOptions.options();
       option.returnNew(true);
       return mongoTemplate.findAndModify(query,update,option,Agre.class);
    }
}
