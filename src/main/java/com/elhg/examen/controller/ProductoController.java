package com.elhg.examen.controller;


import com.elhg.examen.dto.ProductoDto;
import com.elhg.examen.service.IProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    IProductoService productoService;

    @GetMapping
    public ResponseEntity findAllProducto(){
        List<ProductoDto> productoDtoList = productoService.findAll();
        return new ResponseEntity<>(productoDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity findProducto(@PathVariable("id") Long id){
        ProductoDto productoDtoResponse = productoService.findProductoById(id);
        if(productoDtoResponse==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(productoDtoResponse,HttpStatus.OK);
        }
    }


    @PostMapping
    public ResponseEntity saveNewProducto(@RequestBody @Validated ProductoDto productoDto){
        ProductoDto productoDtoResponse = productoService.saveProducto(productoDto);
        if(productoDtoResponse==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(productoDtoResponse,HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProducto(@RequestBody @Validated ProductoDto productoDto, @PathVariable("id") Long id){
        ProductoDto productoDtoResponse = productoService.updateProducto(id, productoDto);
        if(productoDtoResponse==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(productoDtoResponse,HttpStatus.OK);
        }

    }



}
