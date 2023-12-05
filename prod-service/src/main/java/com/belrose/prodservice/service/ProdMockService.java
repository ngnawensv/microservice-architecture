package com.belrose.prodservice.service;


import com.belrose.prodservice.model.prods.ProdRequest;
import com.belrose.prodservice.model.prods.ProdResponse;

public interface ProdMockService {
    ProdResponse saveProductMock(ProdRequest prodRequest);
}
