package com.rafalabs.socialcm.content

import com.rafalabs.socialcm.content.controller.ContentController
import com.rafalabs.socialcm.content.controller.dto.ContentDTO
import com.rafalabs.socialcm.content.domain.Content
import com.rafalabs.socialcm.content.repository.ContentRepository
import com.rafalabs.socialcm.content.repository.entity.ContentEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.TestPropertySource


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations= ["classpath:application-test.properties"])
class ContentControllerTest {

    @LocalServerPort
    private var port: Int = 0;

    private val baseUrl = "http://localhost";

    @Autowired
    private lateinit var restClient: TestRestTemplate;

    @Test
    internal fun should_FindContent_When_IdExists() {

        // Given
        val id = 1L;

        // When
        val response: ResponseEntity<ContentDTO> =
            restClient
                .getForEntity(
                    """${ContentController.BASE_URL}/${id}""",
                    ContentDTO::class
                );

        // Then
        assertEquals(HttpStatus.OK, response.statusCode);
        assertEquals("Title 1", response.body?.title);
    }
}