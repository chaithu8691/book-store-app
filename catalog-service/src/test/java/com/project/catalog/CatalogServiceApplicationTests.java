package com.project.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@TestPropertySource(locations = "/foo.properties")
class CatalogServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
