package com.belrose.prodservice.controller;


import com.belrose.prodservice.model.prods.ProdRequest;
import com.belrose.prodservice.model.prods.ProdResponse;
import com.belrose.prodservice.service.ProdMockService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prod")
public class ProdMockController {

    private final ProdMockService prodMockService;

    public ProdMockController(ProdMockService prodMockService) {
        this.prodMockService = prodMockService;
    }

    @PostMapping(path = "/save",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdResponse> saveProductMock(@RequestBody ProdRequest prodRequest){
        return ResponseEntity.ok(prodMockService.saveProductMock(prodRequest));
    }
}
