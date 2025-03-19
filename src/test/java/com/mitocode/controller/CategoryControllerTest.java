package com.mitocode.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.dto.CategoryDTO;
import com.mitocode.exception.ModelNotFoundException;
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

import org.junit.jupiter.api.Assertions;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ICategoryService service;

    @MockitoBean
    private MapperUtil mapperUtil;

    @Autowired
    private ObjectMapper objectMapper;

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

        List<CategoryDTO> dto = Arrays.asList(CATEGORYDTO_1, CATEGORYDTO_2, CATEGORYDTO_3);
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
    void readByIdTest() throws Exception {
        final int ID = 1;

        //Mockito.when(service.findById(ID)).thenReturn(CATEGORY_1);
        Mockito.when(service.findById(ArgumentMatchers.any())).thenReturn(CATEGORY_1);
        Mockito.when(mapperUtil.map(CATEGORY_1, CategoryDTO.class, "categoryModelMapper")).thenReturn(CATEGORYDTO_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/categories/" + ID)
                        .content(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                // .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].nameofCategory", Matchers.is("TV")));
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameofCategory", Matchers.is("TV")));
    }

    @Test
    void createTest() throws Exception {
        Mockito.when(service.save(ArgumentMatchers.any())).thenReturn(CATEGORY_3);
        Mockito.when(mapperUtil.map(CATEGORY_3, CategoryDTO.class, "categoryModelMapper")).thenReturn(CATEGORYDTO_3);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/categories")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(CATEGORYDTO_3))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.enabledCategory", Matchers.is(true)));
    }

    @Test
    void updateTest() throws Exception{
        final int ID = 2;
        Mockito.when(service.update(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(CATEGORY_2);
        Mockito.when(mapperUtil.map(CATEGORY_2, CategoryDTO.class, "categoryModelMapper")).thenReturn(CATEGORYDTO_2);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/categories/" + ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(CATEGORY_2))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.enabledCategory", Matchers.is(true)));
    }
    @Test
    void updateErrorTest() throws Exception{
        final int ID = 99;

        Mockito.when(service.update(ArgumentMatchers.any(), ArgumentMatchers.any())).thenThrow(new ModelNotFoundException("ID NO VALID: " + ID));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/categories/" + ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(CATEGORY_2))
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                //.andExpect(result -> Assertions.assertInstanceOf(ModelNotFoundException.class, result.getResolvedException()));
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof ModelNotFoundException));
    }

    @Test
    void deleteTest() throws Exception{
        final int ID = 1;

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/categories/" + ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void deleteErrorTest() throws Exception{
        final int ID = 99;

        Mockito.doThrow(new ModelNotFoundException("ID NOT VALID: " + ID)).when(service).delete(ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/categories/" + ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
