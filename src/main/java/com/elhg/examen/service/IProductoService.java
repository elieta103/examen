package com.elhg.examen.service;

import com.elhg.examen.dto.ProductoDto;

import java.util.List;

public interface IProductoService {
    ProductoDto findProductoById(Long id);
    List<ProductoDto> findAll();
    ProductoDto saveProducto(ProductoDto productoDto);
    ProductoDto updateProducto(Long id, ProductoDto productoDto);
}
