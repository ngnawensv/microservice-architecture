package com.belrose.docservice.controller;

import com.belrose.docservice.model.drools.FDRequest;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FixedDepositRateController {

    private final KieContainer kieContainer;
    @Autowired
    private KieSession session;

    public FixedDepositRateController(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @RequestMapping(value = "/getFDInterestRate", method = RequestMethod.GET, produces = "application/json")
    public FDRequest getQuestions(@RequestParam(required = true) String bank, @RequestParam(required = true) Integer durationInYear) {
        KieSession kieSession = kieContainer.newKieSession();
        var fdRequest = new FDRequest(bank,durationInYear);
        session.insert(fdRequest);
        session.fireAllRules();
       kieSession.dispose();
        return fdRequest;
    }
}
