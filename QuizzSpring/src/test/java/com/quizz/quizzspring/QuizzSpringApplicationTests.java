package com.quizz.quizzspring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@SpringBootTest
class QuizzSpringApplicationTests {

    private Logger logger = Logger.getLogger(QuizzSpringApplicationTests.class.getName());

	@Test
	void contextLoads() {
        logger.info("contextLoads running");
	}

}
