package com.mitocode.repository;


import com.mitocode.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends IGenericRepository <Product, Integer> {

    //SQL
    //Select * From Product p Inner Join Category c on p.id_category = c.id_category
    //Where c.name = ?

    @Query("From Product p Where p.category.name = :name")
    List<Product> getProductByCategory(String name);
}
