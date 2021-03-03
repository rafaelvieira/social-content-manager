package com.rafalabs.socialcm

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(locations = ["file:/src/test/resources/application-test.properties"])
class SocialContentManagementApplicationTests {

	@Test
	fun contextLoads() {
	}

}
