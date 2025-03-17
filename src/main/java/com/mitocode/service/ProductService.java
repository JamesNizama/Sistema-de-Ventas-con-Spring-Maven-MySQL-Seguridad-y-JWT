package com.mitocode.service;

import com.mitocode.model.Product;
import com.mitocode.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

//    //@Autowired
//    private final ProductRepository repo;
//
////    public ProductService(ProductRepository repo) {
////        this.repo = repo;
////    }
//
//    public Product validAndSave(Product product) {
//        if (product.getIdProduct() == 0) {
////            repo = new ProductRepository();
//            return repo.saveProduct(product);
//        } else {
//            return new Product(99,"Default","Default",1.0,true);
//        }
//    }
}
