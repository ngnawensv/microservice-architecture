package com.belrose.docservice.service.impl;

import com.belrose.docservice.model.CheckRequest;
import com.belrose.docservice.service.AgreEncodedCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AgreEncodedCheckServiceImpl  implements AgreEncodedCheckService {
    @Override
    public List<String> checkAgreEncoded(List<CheckRequest> checkRequestList) {
        return checkRequestList.stream().map(CheckRequest::getCheckingElement).toList() ;
    }
}
