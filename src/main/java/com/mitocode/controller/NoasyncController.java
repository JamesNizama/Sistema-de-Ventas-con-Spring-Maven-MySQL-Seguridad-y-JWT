package com.mitocode.controller;

import com.mitocode.model.Product;
import com.mitocode.service.impl.ProductNoAsyncServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/noasync")
@RequiredArgsConstructor
public class NoasyncController {

    private final ProductNoAsyncServiceImpl service;

    @GetMapping
    public List<Product> getAllProducts()throws Exception{
        List<Product> list1 = service.getProduct1();
        List<Product> list2 = service.getProduct1();
        List<Product> list3 = service.getProduct1();

        List<Product> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        list.addAll(list3);
        return list;
    }

}
