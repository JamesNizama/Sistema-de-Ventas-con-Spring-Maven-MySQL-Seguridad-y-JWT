package com.mitocode.config;

import com.mitocode.dto.CategoryDTO;
import com.mitocode.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("ModelMapper")
    public ModelMapper ModelMapper() {
        return new ModelMapper();
    }

    @Bean("categoryModelMapper")
    public ModelMapper categoryModelMapper() {

        ModelMapper mapper = new ModelMapper();

        //Handle Miss Matches
        //Lectura
        mapper.createTypeMap(Category.class, CategoryDTO.class)
                .addMapping(Category::getName, (dest, obj) -> dest.setNameofCategory((String) obj));

        //Escritura
        mapper.createTypeMap(CategoryDTO.class, Category.class)
                .addMapping(CategoryDTO::getNameofCategory, (dest, obj) -> dest.setName((String) obj ));

        return mapper;
    }
}
