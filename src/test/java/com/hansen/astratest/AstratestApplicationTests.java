package com.hansen.astratest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AstratestApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void applicationContextTest() {
		AstratestApplication.main(new String[]{});
	}
}
