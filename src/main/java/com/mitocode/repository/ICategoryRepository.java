package com.mitocode.repository;

import com.mitocode.model.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends IGenericRepository<Category, Integer> {

    //===============Describe Queries===============
    //SELECT * FROM CATEGORY C WHERE C.NAME = ''
    List<Category> findByName(String name);

    //SELECT * FROM CATEGORY C WHERE C.IDCATEGORY = ''
    List<Category> findByIdCategory(Integer id);

    //SELECT * FROM CATEGORY C WHERE C.NAME LIKE %ABC%
    // %ABC% -> findByNameContains
    // %ABC -> findByNameStartWith
    // ABC% -> findByNameEndWith
    List<Category> findByNameLike(String name);

    //SELECT * FROM CATEGORY C WHERE C.NAME = '' AND C.ENABLED = ''
    List<Category> findByNameAndEnabled(String name, boolean enabled);

    List<Category> findByNameOrEnabled(String name, boolean enabled);

    List<Category> findByEnabled(boolean enabled);

    List<Category> findByEnabledTrue();

    List<Category> findByEnabledFalse();

    //Category findOneByName(String name);
    Optional<Category> findOneByName(String name);

    List<Category> findTop3ByName(String name);

    List<Category> findByNameIs(String name);

    List<Category> findByNameIsNot(String name);

    List<Category> findByNameIsNull();

    List<Category> findByNameIsNotNull();

    List<Category> findByNameEqualsIgnoreCase(String name);

    List<Category> findByIdCategoryLessThan(Integer id);

    List<Category> findByIdCategoryLessThanEqual(Integer id);

    List<Category> findByIdCategoryGreaterThan(Integer id);

    List<Category> findByIdCategoryGreaterThanEqual(Integer id);

    List<Category> findByIdCategoryBetween(Integer id1, Integer id2);

    List<Category> findByNameOrderByDescription(String name);

    List<Category> findByNameOrderByDescriptionDesc(String name);

    List<Category> findByNameOrderByDescriptionAsc(String name);

    //=============JPA: Java Persintece Queries Language ==============
    @Query("From Category c where c.name = :name and c.description Like %:desc%")
    List<Category> getNameAndDescription1(@Param("name") String name, @Param("desc") String description);

    @Query("Select new Category(c.name, c.description, c.enabled) From Category c where c.name = :name and c.description Like %:desc%")
    List<Category> getNameAndDescription2(@Param("name") String name, @Param("desc") String description);

    //=============SQL: NativeQuery==============
    @Query(value = "Select * From Category c Where c.name= : name", nativeQuery = true)
    List<Category> getNameSQL(@Param("name") String name);

    @Modifying
    @Query(value = "Update Category Set name = :name", nativeQuery = true)
    List<Category> updateNameSQL(@Param("name") String name);
}
