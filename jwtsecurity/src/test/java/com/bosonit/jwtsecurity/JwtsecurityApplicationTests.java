package com.bosonit.jwtsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@SpringBootTest
class JwtsecurityApplicationTests {

	Logger logger = Logger.getLogger(JwtsecurityApplicationTests.class.getName());

	@Test
	void contextLoads() {
        logger.info("contextLoads is running");
	}
}