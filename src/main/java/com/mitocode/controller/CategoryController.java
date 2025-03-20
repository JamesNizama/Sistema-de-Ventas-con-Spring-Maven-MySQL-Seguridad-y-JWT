package com.mitocode.controller;

import com.mitocode.dto.CategoryDTO;
import com.mitocode.dto.GenericResponse;
import com.mitocode.model.Category;
import com.mitocode.service.ICategoryService;
import com.mitocode.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

//    @Qualifier("categoryModelMapper")
//    private final ModelMapper model;

    private final ICategoryService service;
    private final MapperUtil mapperUtil;

    @PreAuthorize("@authService.hasAccess()")
    //@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> readAll() throws Exception {
//        List<CaregoryRecord> list = service.findAll()
//                .stream()
//                .map(e ->
//                        new CaregoryRecord(e.getIdCategory(), e.getName(),e.getDescription(),e.isEnabled()))
//                .toList();
//        List<CategoryDTO> list = service.findAll()
//                .stream()
////                .map(e -> convertToDto(e)).toList();
//                .map(this::convertToDto).toList();
        List<CategoryDTO> list = mapperUtil.mapList(service.findAll(), CategoryDTO.class, "categoryModelMapper");
        return ResponseEntity.ok(list);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Category obj = service.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, CategoryDTO.class, "categoryModelMapper"));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@Valid @RequestBody CategoryDTO categoryDto) throws Exception {
        Category obj = service.save(mapperUtil.map(categoryDto, Category.class, "categoryModelMapper"));
        return new ResponseEntity<>(mapperUtil.map(obj, CategoryDTO.class, "categoryModelMapper"), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody CategoryDTO categoryDTO) throws Exception {
        //categoryDTO.setIdCategory(id);
        Category obj = service.update(id, mapperUtil.map(categoryDTO, Category.class, "categoryModelMapper"));
        return new ResponseEntity<>(mapperUtil.map(obj, CategoryDTO.class, "categoryModelMapper"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //return ResponseEntity.noContent().build();
    }

    // ===========QUERIES===========
    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<CategoryDTO>> findByName(@PathVariable("name") String name) throws Exception {
        List<CategoryDTO> list =
                mapperUtil.mapList(service.findByName(name), CategoryDTO.class, "categoryModelMapper");
        return ResponseEntity.ok(list);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<List<CategoryDTO>> findById(@PathVariable("id") Integer id) throws Exception {
        List<CategoryDTO> list =
                mapperUtil.mapList(service.findByIdCategory(id), CategoryDTO.class, "categoryModelMapper");
        return ResponseEntity.ok(list);
    }

    @GetMapping("/find/name/like/{name}")
    public ResponseEntity<List<CategoryDTO>> findByNameLike(@PathVariable("name") String name) throws Exception {
        List<CategoryDTO> list =
                mapperUtil.mapList(service.findByNameLike(name), CategoryDTO.class, "categoryModelMapper");
        return ResponseEntity.ok(list);
    }

//    @GetMapping("/find/nameAndEnabled/{name}/{enabled}")
//    public ResponseEntity<List<CategoryDTO>> findByNameAndEnabled(@PathVariable("name") String name, @PathVariable("enabled") boolean enabled) throws Exception{
//        List<CategoryDTO> list =
//                mapperUtil.mapList(service.findByNameAndEnabled(name, enabled), CategoryDTO.class, "categoryModelMapper");
//        return ResponseEntity.ok(list);
//    }

    @GetMapping("/find/name/enabled")
    public ResponseEntity<List<CategoryDTO>> findByNameAndEnabled(@RequestParam("name") String name, @RequestParam("enabled") boolean enabled) throws Exception {
        List<CategoryDTO> list =
                mapperUtil.mapList(service.findByNameAndEnabled(name, enabled), CategoryDTO.class, "categoryModelMapper");
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/name/desc")
    public ResponseEntity<List<CategoryDTO>> getNameAndDescription1(@RequestParam("name") String name, @RequestParam("desc") String description) {
        List<CategoryDTO> list =
                mapperUtil.mapList(service.getNameAndDescription1(name, description), CategoryDTO.class, "categoryModelMapper");
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/name/desc2")
    public ResponseEntity<List<CategoryDTO>> getNameAndDescription2(@RequestParam("name") String name, @RequestParam("desc2") String description) {
        List<CategoryDTO> list =
                mapperUtil.mapList(service.getNameAndDescription2(name, description), CategoryDTO.class, "categoryModelMapper");
        return ResponseEntity.ok(list);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<CategoryDTO>> findPage(Pageable pageable){
        Page<CategoryDTO> page = service.findPage(pageable)
                .map(e -> mapperUtil.map(e, CategoryDTO.class, "categoryModelMapper"));
        return ResponseEntity.ok(page);
    }

    @GetMapping("/pagination2")
    public ResponseEntity<Page<CategoryDTO>> findPage2(
            @RequestParam(name = "p", defaultValue = "0") int page,
            @RequestParam(name = "s", defaultValue = "2") int size
    ){
        Page<CategoryDTO> pageDTO = service.findPage(PageRequest.of(page, size))
                .map(e -> mapperUtil.map(e, CategoryDTO.class, "categoryModelMapper"));
        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping("/order")
    public ResponseEntity<List<CategoryDTO>>
    findAllOrder(@RequestParam(name = "param", defaultValue = "ASC") String param){
        List<CategoryDTO> list = mapperUtil.mapList(service.findAllOrder(param),
                CategoryDTO.class, "categoryModelMapper");
        return ResponseEntity.ok(list);
    }

//    private CategoryDTO convertToDto(Category obj) {
//        return model.map(obj, CategoryDTO.class);
//    }
//
//    private Category convertToEntity(CategoryDTO dto) {
//        return model.map(dto, Category.class);
//    }

}
