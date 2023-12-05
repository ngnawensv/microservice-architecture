package com.belrose.agreservice.service;

import com.belrose.agreservice.model.Agre;
import com.belrose.agreservice.model.AgreResponse;

public interface AgreService {
    AgreResponse saveAgreAndAgreEncoded(Agre agre);
}
