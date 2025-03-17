package com.mitocode.controller;

import com.mitocode.dto.ProdcutDTO;
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
    public ResponseEntity<List<ProdcutDTO>> readAll() throws Exception {
        List<ProdcutDTO> list = service.findAll()
                .stream()
                .map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdcutDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Product obj = service.findById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @PostMapping
    public ResponseEntity<ProdcutDTO> save(@Valid @RequestBody ProdcutDTO productDto) throws Exception {
        Product obj = service.save(convertToEntity(productDto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdcutDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ProdcutDTO productDTO) throws Exception {
        Product obj = service.update(id, convertToEntity(productDTO));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //return ResponseEntity.noContent().build();
    }

    private ProdcutDTO convertToDto(Product obj) {
        return model.map(obj, ProdcutDTO.class);
    }

    private Product convertToEntity(ProdcutDTO dto) {
        return model.map(dto, Product.class);
    }

    //================================
    @GetMapping("/get/name")
    public ResponseEntity<List<ProdcutDTO>> getNameProduct(@RequestParam("name") String name){
        List<ProdcutDTO> list =
                mapperUtil.mapList(service.getProductByCategory(name), ProdcutDTO.class);
        return ResponseEntity.ok(list);
    }

}
