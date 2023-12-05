package com.belrose.prodservice.service;


import com.belrose.prodservice.model.prods.ProdRequest;
import com.belrose.prodservice.model.prods.ProdResponse;

public interface ProdService {
    ProdResponse saveProduct(ProdRequest prodRequest);
}
