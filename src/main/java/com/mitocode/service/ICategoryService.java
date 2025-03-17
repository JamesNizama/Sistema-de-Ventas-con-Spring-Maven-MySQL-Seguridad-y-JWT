package com.mitocode.service;

import com.mitocode.dto.CategoryDTO;
import com.mitocode.model.Category;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryService extends ICRUD <Category, Integer>{

//    List<Category> findAll() throws Exception;
//    Category findById(Integer id) throws Exception;
//    Category save(Category category) throws Exception;
//    Category update(Integer id, Category category) throws Exception;
//    void delete(Integer id) throws Exception;
    List<Category> findByName(String name);
    List<Category> findByIdCategory(Integer id);
    List<Category> findByNameLike(String name);
    List<Category> findByNameAndEnabled(String name, boolean enabled);
    List<Category> getNameAndDescription1(String name, String description);
    List<Category> getNameAndDescription2(String name, String description);
}
