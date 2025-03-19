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
public class ProductNoAsyncServiceImpl {

    private final IProductRepository repository;

    public List<Product> getProduct1() throws Exception{
        Thread.sleep(1000);
        List<Product> products = repository.findAll();
        return products;
    }


    public List<Product> getProduct2() throws Exception{
        Thread.sleep(3000);
        List<Product> products = repository.findAll();
        return products;
    }


    public List<Product> getProduct3() throws Exception{
        Thread.sleep(2000);
        List<Product> products = repository.findAll();
        return products;
    }

}
