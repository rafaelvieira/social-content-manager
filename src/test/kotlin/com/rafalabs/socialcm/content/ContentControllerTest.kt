package com.rafalabs.socialcm.content

import com.rafalabs.socialcm.content.controller.ContentController
import com.rafalabs.socialcm.content.controller.dto.ContentDTO
import com.rafalabs.socialcm.content.domain.Content
import com.rafalabs.socialcm.content.repository.ContentRepository
import com.rafalabs.socialcm.content.repository.entity.ContentEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
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
    internal fun should_CreateContent_When_Properties_Filled() {
        // Given
        val expectedContent = ContentDTO();
        expectedContent.title = "Learning Kotlin by using TDD";
        expectedContent.description = "How to learn Kotlin through TDD process";
        expectedContent.value = "Some big content";

        // When
        val response: ResponseEntity<Unit> =
            restClient
                .postForEntity(
                    """${ContentController.BASE_URL}""",
                    expectedContent
                );

        // Then
        assertEquals(HttpStatus.CREATED, response.statusCode);
//        assertEquals("Title 1", response.body?.title);
//
//        // Then
//        assertEquals(1,                     persistedContent.id);
//        assertEquals(expectedContent.title,         persistedContent.title);
//        assertEquals(expectedContent.description,   persistedContent.description);
//        assertEquals(expectedContent.value,         persistedContent.value);
    }

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