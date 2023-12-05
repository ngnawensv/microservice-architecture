package com.belrose.reposervice.service.impl;

import com.belrose.reposervice.model.prod.Prod;
import com.belrose.reposervice.repository.ProdRepository;
import com.belrose.reposervice.repository.ProdRepositoryCusto;
import com.belrose.reposervice.service.ProdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ProdServiceImpl implements ProdService {

    private final ProdRepository prodRepository;
    private final ProdRepositoryCusto prodRepositoryCusto;

    public ProdServiceImpl(ProdRepository prodRepository, ProdRepositoryCusto prodRepositoryCusto) {
        this.prodRepository = prodRepository;
        this.prodRepositoryCusto = prodRepositoryCusto;
    }

    @Override
    public Mono<Prod> saveProd(Prod prod) {
        return prodRepository.save(prod);
    }
}
