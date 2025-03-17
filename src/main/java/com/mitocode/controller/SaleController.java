package com.mitocode.controller;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.dto.SaleDTO;
import com.mitocode.model.Sale;
import com.mitocode.service.ISaleService;
import com.mitocode.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final ISaleService service;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> readAll() throws Exception {
        List<SaleDTO> list = mapperUtil.mapList(service.findAll(), SaleDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Sale obj = service.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, SaleDTO.class));
    }

    @PostMapping
    public ResponseEntity<SaleDTO> save(@Valid @RequestBody SaleDTO saleDto) throws Exception {
        Sale obj = service.save(mapperUtil.map(saleDto, Sale.class));
        return new ResponseEntity<>(mapperUtil.map(obj, SaleDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody SaleDTO saleDTO) throws Exception {
        Sale obj = service.update(id, mapperUtil.map(saleDTO, Sale.class));
        return new ResponseEntity<>(mapperUtil.map(obj, SaleDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //=========================
    @GetMapping("/resume1")
    public ResponseEntity<List<ProcedureDTO>> resume1(){
        return ResponseEntity.ok(service.callProcedure1());
    }
    @GetMapping("/resume2")
    public ResponseEntity<List<IProcedureDTO>> resume2(){
        return ResponseEntity.ok(service.callProcedure2());
    }

    @GetMapping("/resume3")
    public ResponseEntity<List<ProcedureDTO>> resume3(){
        return ResponseEntity.ok(service.callProcedure3());
    }

    @GetMapping("/resume4")
    public ResponseEntity<Void> resume4(){
        service.callProcedure4();
        return ResponseEntity.notFound().build();
    }
}
