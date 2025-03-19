package com.mitocode.controller;

import com.mitocode.dto.CategoryDTO;
import com.mitocode.model.Category;
import com.mitocode.service.ICategoryService;
import com.mitocode.util.MapperUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.hamcrest.Matchers;

import java.util.Arrays;
import java.util.List;

import org.mockito.ArgumentMatchers;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ICategoryService service;

    @MockitoBean
    private MapperUtil mapperUtil;

    Category CATEGORY_1 = new Category(1, "TV", "Televisión", true);
    Category CATEGORY_2 = new Category(2, "PSP", "Play Station Portable", true);
    Category CATEGORY_3 = new Category(3, "BOOKS", "Some books", true);

    CategoryDTO CATEGORYDTO_1 = new CategoryDTO(1, "TV", "Televisión", true);
    CategoryDTO CATEGORYDTO_2 = new CategoryDTO(2, "PSP", "Play Station Portable", true);
    CategoryDTO CATEGORYDTO_3 = new CategoryDTO(3, "BOOKS", "Some books", true);

    @Test
    void readAllTest() throws Exception {
        List<Category> categories = Arrays.asList(CATEGORY_1, CATEGORY_2, CATEGORY_3);
        Mockito.when(service.findAll()).thenReturn(categories);

        List<CategoryDTO> dto = Arrays.asList(CATEGORYDTO_1,CATEGORYDTO_2, CATEGORYDTO_3);
        Mockito.when(
                mapperUtil.mapList(categories, CategoryDTO.class, "categoryModelMapper")).thenReturn(dto);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/categories")
                        .content(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
        //.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.hasSize(3)));
        // solo en el caso de tener esta estructura de respuesta de path.
        // response.put("status", 200);
        // response.put("message", "success");
        // response.put("data", categories);
    }

    @Test
    void readByIdTest() throws Exception{
        final int ID = 1;

        //Mockito.when(service.findById(ID)).thenReturn(CATEGORY_1);
        Mockito.when(service.findById(ArgumentMatchers.any())).thenReturn(CATEGORY_1);
        Mockito.when(mapperUtil.map(CATEGORY_1, CategoryDTO.class,"categoryModelMapper" )).thenReturn(CATEGORYDTO_1);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/categories/" + ID)
                .content(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
               // .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].nameofCategory", Matchers.is("TV")));
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameofCategory", Matchers.is("TV")));
    }
}
