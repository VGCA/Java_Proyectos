package com.bosonit.inventario;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@SpringBootTest
class InventarioApplicationTests {

    private Logger logger = Logger.getLogger(InventarioApplicationTests.class.getName());

	@Test
	void contextLoads() {
        logger.info("contextLoads running");
	}

}
