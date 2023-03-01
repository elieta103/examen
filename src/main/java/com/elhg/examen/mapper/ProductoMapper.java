package com.elhg.examen.mapper;

import com.elhg.examen.dto.ProductoDto;
import com.elhg.examen.entity.Producto;
import org.mapstruct.Mapper;

@Mapper
public interface ProductoMapper {
    ProductoDto productoToProductoDto(Producto producto);
    Producto productoDtoToProducto(ProductoDto productoDto);
}
