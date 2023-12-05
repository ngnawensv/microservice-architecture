package com.belrose.docservice.service;

import com.belrose.docservice.model.CheckRequest;

import java.util.List;

public interface AgreEncodedCheckService {
    List<String> checkAgreEncoded(List<CheckRequest> checkRequestList);
}
