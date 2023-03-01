package com.elhg.examen.service;

import com.elhg.examen.dto.ProductoDto;
import com.elhg.examen.entity.Producto;
import com.elhg.examen.mapper.ProductoMapper;
import com.elhg.examen.repository.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductoServiceImpl implements IProductoService{

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ProductoMapper productoMapper;

    @Override
    public ProductoDto findProductoById(Long id) {
        ProductoDto productoEncontrado = null;

        try{
            Producto producto = productoRepository.findById(id).orElseThrow();
            productoEncontrado = productoMapper.productoToProductoDto(producto);
            log.info("Producto Encontrado : {}",productoEncontrado);
        }catch (Exception ex){
            log.error(ex.getMessage());
        }

        return productoEncontrado;
    }

    @Override
    public List<ProductoDto> findAll() {
        List<ProductoDto> productoDtoList = productoRepository.findAll()
                .stream().map(item -> productoMapper.productoToProductoDto(item)).collect(Collectors.toList());

        return productoDtoList;
    }

    @Override
    public ProductoDto saveProducto(ProductoDto productoDto){
        Producto producto= productoMapper.productoDtoToProducto(productoDto);

        Producto productoSaved = null;
        ProductoDto productoDtoSaved= null;
        try{
            productoSaved = productoRepository.save(producto);
            productoDtoSaved = productoMapper.productoToProductoDto(productoSaved);
            log.info("Producto Guardado : {}", productoDtoSaved);
        }catch (Exception ex){
            log.error(ex.getMessage());
        }

        return productoDtoSaved;
    }


    @Override
    public ProductoDto updateProducto(Long id, ProductoDto productoDto){
        Optional<Producto> productoEncontrado = productoRepository.findById(id);
        Producto productoSaved = null;
        ProductoDto productoDtoSaved= null;

        if(productoEncontrado.isPresent()){
            Producto productoUpdate = new Producto();
            productoUpdate.setId(id);
            productoUpdate.setNombre(productoEncontrado.get().getNombre());
            productoUpdate.setDescripcion(productoEncontrado.get().getDescripcion());
            productoUpdate.setCodigo(productoEncontrado.get().getCodigo());
            productoUpdate.setActivo(productoEncontrado.get().isActivo());
            productoUpdate.setPrecio(productoEncontrado.get().getPrecio());
            productoUpdate.setFechaCreacion(productoEncontrado.get().getFechaCreacion());
            try{
                productoSaved = productoRepository.save(productoUpdate);
                productoDtoSaved = productoMapper.productoToProductoDto(productoSaved);
                log.info("Producto Guardado : {}", productoDtoSaved);
            }catch (Exception ex){
                log.error(ex.getMessage());
            }

        }

        return productoDtoSaved;
    }



}
