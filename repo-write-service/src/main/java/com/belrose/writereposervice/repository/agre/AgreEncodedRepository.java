package com.belrose.writereposervice.repository.agre;

import com.belrose.writereposervice.model.agre.AgreEncoded;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AgreEncodedRepository  extends ReactiveMongoRepository<AgreEncoded,String> {
}
