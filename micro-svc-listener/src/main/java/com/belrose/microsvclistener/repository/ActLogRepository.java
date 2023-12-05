package com.belrose.microsvclistener.repository;

import com.belrose.microsvclistener.model.actlog.ActLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActLogRepository extends ReactiveMongoRepository<ActLog,String> {
}
