package com.mitocode.repository;


import com.mitocode.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends IGenericRepository <Client, Integer> {
}
