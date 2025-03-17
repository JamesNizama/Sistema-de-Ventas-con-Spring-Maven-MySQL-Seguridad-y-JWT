package com.mitocode.service.impl;

import com.mitocode.dto.CategoryDTO;
import com.mitocode.model.Category;
import com.mitocode.repository.ICategoryRepository;
import com.mitocode.repository.IGenericRepository;
import com.mitocode.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends ICRUDImpl <Category, Integer> implements ICategoryService {

    private final ICategoryRepository repository;

    @Override
    protected IGenericRepository<Category, Integer> getRepository() {
        return repository;
    }

    @Override
    public List<Category> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Category> findByIdCategory(Integer id) {
        return repository.findByIdCategory(id);
    }

    @Override
    public List<Category> findByNameLike(String name) {
        return repository.findByNameLike("%" + name + "%");
    }

    @Override
    public List<Category> findByNameAndEnabled(String name, boolean enabled) {
        return repository.findByNameAndEnabled(name, enabled);
    }

    @Override
    public List<Category> getNameAndDescription1(String name, String description) {
        return repository.getNameAndDescription1(name, description);
    }

    @Override
    public List<Category> getNameAndDescription2(String name, String description) {
        return repository.getNameAndDescription2(name, description);
    }
}
