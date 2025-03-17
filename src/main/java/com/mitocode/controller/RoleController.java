package com.mitocode.controller;

import com.mitocode.dto.RoleDTO;
import com.mitocode.model.Role;
import com.mitocode.service.IRoleService;
import com.mitocode.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService service;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> readAll() throws Exception {
        List<RoleDTO> list = mapperUtil.mapList(service.findAll(), RoleDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Role obj = service.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, RoleDTO.class));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> save(@Valid @RequestBody RoleDTO roleDto) throws Exception {
        Role obj = service.save(mapperUtil.map(roleDto, Role.class));
        return new ResponseEntity<>(mapperUtil.map(obj, RoleDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody RoleDTO roleDTO) throws Exception {
        Role obj = service.update(id, mapperUtil.map(roleDTO, Role.class));
        return new ResponseEntity<>(mapperUtil.map(obj, RoleDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
