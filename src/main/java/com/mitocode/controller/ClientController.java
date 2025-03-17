package com.mitocode.controller;

import com.mitocode.dto.ClientDTO;
import com.mitocode.model.Client;
import com.mitocode.service.IClientService;
import com.mitocode.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService service;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> readAll() throws Exception {
        List<ClientDTO> list = mapperUtil.mapList(service.findAll(), ClientDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Client obj = service.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ClientDTO.class));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> save(@Valid @RequestBody ClientDTO clientDto) throws Exception {
        Client obj = service.save(mapperUtil.map(clientDto, Client.class));
        return new ResponseEntity<>(mapperUtil.map(obj, ClientDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ClientDTO clientDTO) throws Exception {
        Client obj = service.update(id, mapperUtil.map(clientDTO, Client.class));
        return new ResponseEntity<>(mapperUtil.map(obj, ClientDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
