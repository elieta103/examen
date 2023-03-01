package com.elhg.examen;

import static org.assertj.core.api.Assertions.assertThat;

import com.elhg.examen.controller.ProductoController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExamenApplicationTests {

	@Autowired
	private ProductoController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
