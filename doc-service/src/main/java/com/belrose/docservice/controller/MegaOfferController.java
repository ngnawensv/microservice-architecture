package com.belrose.docservice.controller;

import com.belrose.docservice.model.drools.Order;
import com.belrose.docservice.util.Translator;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MegaOfferController {
    @Autowired
    private KieSession session;

    @PostMapping("/order")
    public Order orderNow(@RequestBody Order order){
        log.info(Translator.toLocal("test"));
        session.insert(order);
        session.fireAllRules();
        return order;
    }
}
