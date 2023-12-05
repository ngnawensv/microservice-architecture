package com.belrose.prodservice.service.impl;

import com.belrose.prodservice.model.prods.ProdRequest;
import com.belrose.prodservice.model.prods.ProdResponse;
import com.belrose.prodservice.service.ProdMockService;
import com.belrose.prodservice.util.JsonToResponseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.belrose.prodservice.constantes.MockDataPath.PATH_TO_MOCK_PRODUCT_RESPONSE;
import static com.belrose.prodservice.constantes.MockDataPath.getName;

@Service
@Slf4j
public class ProdMockServiceImpl implements ProdMockService {
    @Override
    public ProdResponse saveProductMock(ProdRequest prodRequest) {
        log.info("Info {} : ",PATH_TO_MOCK_PRODUCT_RESPONSE);
        log.debug("Debug {} : ",PATH_TO_MOCK_PRODUCT_RESPONSE);
        return JsonToResponseMapper.saveProduct(getName(PATH_TO_MOCK_PRODUCT_RESPONSE));
    }
}
