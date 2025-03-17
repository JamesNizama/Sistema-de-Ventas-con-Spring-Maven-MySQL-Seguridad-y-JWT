package com.mitocode.controller;

import com.mitocode.dto.ProviderDTO;
import com.mitocode.model.Provider;
import com.mitocode.service.IProviderService;
import com.mitocode.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final IProviderService service;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ProviderDTO>> readAll() throws Exception {
        List<ProviderDTO> list = mapperUtil.mapList(service.findAll(), ProviderDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Provider obj = service.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ProviderDTO.class));
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> save(@Valid @RequestBody ProviderDTO providerDto) throws Exception {
        Provider obj = service.save(mapperUtil.map(providerDto, Provider.class));
        return new ResponseEntity<>(mapperUtil.map(obj, ProviderDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ProviderDTO providerDTO) throws Exception {
        Provider obj = service.update(id, mapperUtil.map(providerDTO, Provider.class));
        return new ResponseEntity<>(mapperUtil.map(obj, ProviderDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
