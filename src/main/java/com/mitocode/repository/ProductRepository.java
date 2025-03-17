package com.mitocode.repository;

import com.mitocode.model.Product;
import org.springframework.stereotype.Repository;

//@Component
//@Controller
//@Service
//@Repository
public class ProductRepository {

    public Product saveProduct(Product product) {
        System.out.println("Saving prodcut -> " + product);
        return product;
    }

}
