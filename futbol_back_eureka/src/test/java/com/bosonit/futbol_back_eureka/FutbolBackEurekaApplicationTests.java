package com.bosonit.futbol_back_eureka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@SpringBootTest
class FutbolBackEurekaApplicationTests {

    private Logger logger = Logger.getLogger(FutbolBackEurekaApplicationTests.class.getName());


    @Test
	void contextLoads() {
        logger.info("context is running");
	}

}
