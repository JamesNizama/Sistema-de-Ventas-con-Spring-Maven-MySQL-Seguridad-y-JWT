package com.mitocode.service.impl;

import com.mitocode.model.Provider;
import com.mitocode.repository.IGenericRepository;
import com.mitocode.repository.IProviderRepository;
import com.mitocode.service.IProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl extends ICRUDImpl <Provider, Integer> implements IProviderService {

    private final IProviderRepository repository;

    @Override
    protected IGenericRepository<Provider, Integer> getRepository()
    {
        return repository;
    }
}
