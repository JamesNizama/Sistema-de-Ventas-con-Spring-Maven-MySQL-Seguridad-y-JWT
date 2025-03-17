package com.mitocode.service.impl;

import com.mitocode.model.Client;
import com.mitocode.repository.IClientRepository;
import com.mitocode.repository.IGenericRepository;
import com.mitocode.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends ICRUDImpl <Client, Integer> implements IClientService {

    private final IClientRepository repository;

    @Override
    protected IGenericRepository<Client, Integer> getRepository()
    {
        return repository;
    }
}
