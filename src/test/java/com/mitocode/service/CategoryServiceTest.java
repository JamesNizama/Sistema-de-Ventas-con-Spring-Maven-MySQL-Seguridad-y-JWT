package com.mitocode.service;

import com.mitocode.model.Category;
import com.mitocode.repository.ICategoryRepository;
import com.mitocode.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

    @MockitoBean
    private ICategoryService service;

    @MockitoBean
    private ICategoryRepository repository;

    private Category CATEGORY_1;
    private Category CATEGORY_2;
    private Category CATEGORY_3;

    @BeforeEach
    public  void init(){
        MockitoAnnotations.openMocks(this);
        this.service = new CategoryServiceImpl(repository);

        CATEGORY_1 = new Category(1,"TV", "Televisión", true);
        CATEGORY_2 = new Category(2,"PSP", "Play Station", true);
        CATEGORY_3 = new Category(3,"BOOKS", "Some books", true);

        List<Category> categories = List.of(CATEGORY_1, CATEGORY_2, CATEGORY_3);
        Mockito.when(repository.findAll()).thenReturn(categories);//  void readAllTest() throws Exception
        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(CATEGORY_1));//void readByIdTest() throws Exception{
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(CATEGORY_1); //  void saveTest() throws Exception //  void updateTest() throws Exception{
    }

    @Test
    void readAllTest() throws Exception {
        List<Category> response = service.findAll();
//        Assertions.assertNotNull(response);
//        Assertions.assertTrue(response.isEmpty());
        Assertions.assertEquals(response.size(), 3);
    }

    @Test
    void readByIdTest() throws Exception{
        final int ID = 1;
        Category response = service.findById(ID);
        Assertions.assertNotNull(response);
    }

    @Test
    void saveTest() throws Exception{
        Category response = service.save(CATEGORY_1);
        Assertions.assertNotNull(response);
    }

    @Test
    void updateTest() throws Exception{
        final int ID = 1;
        Category response = service.update(ID, CATEGORY_1);
        Assertions.assertNotNull(response);
    }

    @Test
    void deleteTest() throws Exception{
        final int ID = 1;
        service.delete(ID);
        Mockito.verify(repository, Mockito.times(1)).deleteById(ID);
    }

}
