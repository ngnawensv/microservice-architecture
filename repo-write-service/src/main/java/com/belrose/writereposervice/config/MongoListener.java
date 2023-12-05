package com.belrose.writereposervice.config;

import com.belrose.writereposervice.model.docs.Docs;
import com.github.f4b6a3.ulid.UlidCreator;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class MongoListener extends AbstractMongoEventListener<Docs> {
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Docs> event) {
        super.onBeforeConvert(event);
        var docs=event.getSource();
        if (Strings.isBlank(docs.getId())) {
            docs.setId(UlidCreator.getUlid().toLowerCase());
        }
    }
}
