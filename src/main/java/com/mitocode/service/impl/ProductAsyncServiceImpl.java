package com.mitocode.service.impl;

import com.mitocode.model.Product;
import com.mitocode.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductAsyncServiceImpl {

    private final IProductRepository repository;

    @Async
    public CompletableFuture<List<Product>> getProduct1() throws Exception{
        Thread.sleep(1000);
        List<Product> products = repository.findAll();
        log.info("Async request IMPL Thread: " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(products);
    }

    @Async
    public CompletableFuture<List<Product>> getProduct2() throws Exception{
        Thread.sleep(3000);
        List<Product> products = repository.findAll();

        return CompletableFuture.completedFuture(products);
    }

    @Async
    public CompletableFuture<List<Product>> getProduct3() throws Exception{
        Thread.sleep(2000);
        List<Product> products = repository.findAll();

        return CompletableFuture.completedFuture(products);
    }

}
