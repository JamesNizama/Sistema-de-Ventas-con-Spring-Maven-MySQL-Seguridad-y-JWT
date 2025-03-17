package com.mitocode.service.impl;

import com.mitocode.model.Product;
import com.mitocode.repository.IGenericRepository;
import com.mitocode.repository.IProductRepository;
import com.mitocode.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ICRUDImpl <Product, Integer> implements IProductService {

    private final IProductRepository repository;

    @Override
    protected IGenericRepository<Product, Integer> getRepository() {
        return repository;
    }

    @Override
    public List<Product> getProductByCategory(String name) {
        return repository.getProductByCategory(name);
    }

//    @Override
//    public List<Product> findAll() throws Exception {
//        return repository.findAll();
//    }
//
//    @Override
//    public Product findById(Integer id) throws Exception {
//        return repository.findById(id).orElse(new Product());
//    }
//
//    @Override
//    public Product save(Product product) throws Exception {
//        return repository.save(product);
//    }
//
//    @Override
//    public Product update(Integer id, Product product) throws Exception {
//        product.setIdProduct(id);
//        return repository.save(product);
//    }
//
//    @Override
//    public void delete(Integer id) throws Exception {
//        repository.deleteById(id);
//    }
}
