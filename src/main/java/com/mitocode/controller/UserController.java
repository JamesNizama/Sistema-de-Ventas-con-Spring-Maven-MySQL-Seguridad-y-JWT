package com.mitocode.controller;

import com.mitocode.dto.UserDTO;
import com.mitocode.model.User;
import com.mitocode.service.IUserService;
import com.mitocode.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService service;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<UserDTO>> readAll() throws Exception {
        List<UserDTO> list = mapperUtil.mapList(service.findAll(), UserDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> readById(@PathVariable("id") Integer id) throws Exception {
        User obj = service.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, UserDTO.class));
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO userDto) throws Exception {
        User obj = service.save(mapperUtil.map(userDto, User.class));
        return new ResponseEntity<>(mapperUtil.map(obj, UserDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody UserDTO userDTO) throws Exception {
        User obj = service.update(id, mapperUtil.map(userDTO, User.class));
        return new ResponseEntity<>(mapperUtil.map(obj, UserDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
