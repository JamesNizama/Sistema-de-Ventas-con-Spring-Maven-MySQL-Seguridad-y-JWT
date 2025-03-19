package com.mitocode.controller;

import com.mitocode.dto.CategoryDTO;
import com.mitocode.dto.GenericResponse;
import com.mitocode.dto.ProductDTO;
import com.mitocode.model.Product;
import com.mitocode.service.IProductService;
import com.mitocode.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService service;

    @Qualifier("ModelMapper")
    private final ModelMapper model;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<GenericResponse<ProductDTO>> readAll() throws Exception {
        List<ProductDTO> list = service.findAll()
                .stream()
                .map(this::convertToDto).toList();
        return ResponseEntity.ok(new GenericResponse<>(200, "success", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ProductDTO>> readById(@PathVariable("id") Integer id) throws Exception {
        Product obj = service.findById(id);
        ProductDTO dto = convertToDto(obj);
        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList(dto)));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<ProductDTO>> save(@Valid @RequestBody ProductDTO productDto) throws Exception {
        Product obj = service.save(convertToEntity(productDto));
        ProductDTO dto = convertToDto(obj);
        return new ResponseEntity<>(
                new GenericResponse<>(200, "success", Arrays.asList(dto)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<ProductDTO>> update(@Valid @PathVariable("id") Integer id, @RequestBody ProductDTO productDTO) throws Exception {
        Product obj = service.update(id, convertToEntity(productDTO));
        ProductDTO dto = convertToDto(obj);
        return new ResponseEntity<>(
                new GenericResponse<>(200,"succedd",Arrays.asList(dto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //return ResponseEntity.noContent().build();
    }

    private ProductDTO convertToDto(Product obj) {
        return model.map(obj, ProductDTO.class);
    }

    private Product convertToEntity(ProductDTO dto) {
        return model.map(dto, Product.class);
    }

    //================================
    @GetMapping("/get/name")
    public ResponseEntity<GenericResponse<ProductDTO>> getNameProduct(@RequestParam("name") String name){
        List<ProductDTO> list =
                mapperUtil.mapList(service.getProductByCategory(name), ProductDTO.class);

        return ResponseEntity.ok(
                new GenericResponse<>(200, "success", list));
    }

}
