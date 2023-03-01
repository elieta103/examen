package com.elhg.examen.controller;

import com.elhg.examen.dto.ProductoDto;
import com.elhg.examen.service.IProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void saveProductosTest() throws Exception{

        // given - Precondicion
        ProductoDto productoDto = ProductoDto.builder()
                .id(null)
                .nombre("Celular Samsung 10")
                .descripcion("Descripcion de celular de 10")
                .codigo("145000")
                .precio(new BigDecimal("1200.00"))
                .activo(true)
                .build();

        // when - Accion o comportamiento a probar
        ResultActions response = mockMvc.perform(post("/api/producto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productoDto)));

        // then - Verificar Assert con los resultados
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.codigo",
                        is(productoDto.getCodigo())))
                .andExpect(jsonPath("$.nombre",
                        is(productoDto.getNombre())));

    }

    @Test
    public void findAllProductosTest() throws Exception{
        // given - Precondiciones

        // when -  Accion o conducta a probar
        ResultActions response = mockMvc.perform(get("/api/producto"));

        // then - Verificar respuesta
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()").isNotEmpty());

    }

    @Test
    public void findProductoByIdTest() throws Exception{
        // given - Precondicion
        ProductoDto productoDto = ProductoDto.builder()
                .id(null)
                .nombre("Celular Samsung 10")
                .descripcion("Descripcion de celular de 10")
                .codigo("145000")
                .precio(new BigDecimal("1200.00"))
                .activo(true)
                .build();
        productoService.saveProducto(productoDto);

        // when -  Accion o comportamiento a probar
        ResultActions response = mockMvc.perform(get("/api/producto/{id}", 1L));

        // then - Verificar Salida
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nombre", is(productoDto.getNombre())))
                .andExpect(jsonPath("$.codigo", is(productoDto.getCodigo() )));

    }

}