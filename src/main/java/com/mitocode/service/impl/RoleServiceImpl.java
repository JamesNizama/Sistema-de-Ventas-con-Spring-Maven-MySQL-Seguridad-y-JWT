package com.mitocode.service.impl;

import com.mitocode.model.Role;
import com.mitocode.repository.IGenericRepository;
import com.mitocode.repository.IRoleRepository;
import com.mitocode.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ICRUDImpl <Role, Integer> implements IRoleService {

    private final IRoleRepository repository;

    @Override
    protected IGenericRepository<Role, Integer> getRepository()
    {
        return repository;
    }
}
