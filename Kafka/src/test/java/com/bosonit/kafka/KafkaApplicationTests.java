package com.bosonit.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@SpringBootTest
class KafkaApplicationTests {

    private Logger logger = Logger.getLogger(KafkaApplicationTests.class.getName());

    @Test
	void contextLoads() {
        logger.info("contextLoads is running");
	}

}
