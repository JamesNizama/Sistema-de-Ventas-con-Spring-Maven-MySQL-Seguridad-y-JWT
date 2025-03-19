package com.mitocode.controller;

import com.mitocode.model.Product;
import com.mitocode.service.impl.ProductAsyncServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/async")
@RequiredArgsConstructor
@Slf4j
public class AsyncController {

    private final ProductAsyncServiceImpl  service;

    @GetMapping
    public List<Product> getAllProducts() throws Exception{
        CompletableFuture<List<Product>> c1 = service.getProduct1();
        CompletableFuture<List<Product>> c2 = service.getProduct2();
        CompletableFuture<List<Product>> c3 = service.getProduct3();

        CompletableFuture.allOf(c1,c2,c3).join();

        log.info("Async request Thread: " + Thread.currentThread());

        return Stream.of(c1.get(), c2.get(), c3.get())
                .flatMap(e -> e.stream())
                .toList();
    }

}
