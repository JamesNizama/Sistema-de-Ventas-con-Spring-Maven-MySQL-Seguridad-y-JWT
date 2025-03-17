package com.mitocode.repository;


import com.mitocode.model.Provider;
import org.springframework.stereotype.Repository;

@Repository
public interface IProviderRepository extends IGenericRepository<Provider, Integer> {
}
