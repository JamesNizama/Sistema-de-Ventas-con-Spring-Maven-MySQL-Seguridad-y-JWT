package com.mitocode.service.impl;

import com.mitocode.model.User;
import com.mitocode.repository.IUserRepository;
import com.mitocode.repository.IGenericRepository;
import com.mitocode.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ICRUDImpl <User, Integer> implements IUserService {

    private final IUserRepository repository;

    @Override
    protected IGenericRepository<User, Integer> getRepository()
    {
        return repository;
    }
}
