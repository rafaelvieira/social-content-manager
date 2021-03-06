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
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.*
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ContentControllerTest {

    @Autowired
    private lateinit var restClient: TestRestTemplate;

    @Autowired
    private lateinit var contentRepository: ContentRepository;

    @Test
    internal fun should_CreateContent_When_Properties_Filled() {
        // Given
        val expectedContent = ContentDTO();
        expectedContent.title       = "Learning Kotlin by using TDD";
        expectedContent.description = "How to learn Kotlin through TDD process";
        expectedContent.value       = "Some big content";

        // When
        val response =
            restClient
                .postForEntity<Unit>(
                    """${ContentController.BASE_URL}""",
                    expectedContent
                );

        // Then
        assertEquals(HttpStatus.CREATED, response.statusCode);
        assertEquals("/api/content/3", response.headers.location.toString());

        val persistedContent = contentRepository.findById(3).orElse(ContentEntity());

        assertEquals(3,                     persistedContent.id);
        assertEquals(expectedContent.title,         persistedContent.title);
        assertEquals(expectedContent.description,   persistedContent.description);
        assertEquals(expectedContent.value,         persistedContent.value);
    }

    @Test
    internal fun should_UpdateContent_When_Properties_Filled() {
        // Given
        val expectedContent = ContentDTO();
        expectedContent.title       = "Updated - Learning Kotlin by using TDD";
        expectedContent.description = "Updated - How to learn Kotlin through TDD process";
        expectedContent.value       = "Updated - Some big content";

        // When
        val response =
            restClient
                .exchange<Unit>(
                    RequestEntity
                        .put("""${ContentController.BASE_URL}/2""")
                        .body(expectedContent));
//                .exchange(
//                    url = """${ContentController.BASE_URL}/2""",
//                    method = HttpMethod.PUT,
//                    requestEntity = httpEntity,
//                    uriVariables = null
//                );

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.statusCode);

        val persistedContent = contentRepository.findById(2).orElse(ContentEntity());

        assertEquals(2,                     persistedContent.id);
        assertEquals(expectedContent.title,         persistedContent.title);
        assertEquals(expectedContent.description,   persistedContent.description);
        assertEquals(expectedContent.value,         persistedContent.value);
    }

    @Test
    internal fun should_FindContent_When_IdExists() {

        // Given
        val id = 1L;

        // When
        val response =
            restClient
                .getForEntity<ContentDTO>(
                    """${ContentController.BASE_URL}/${id}""",
                    ContentDTO::class
                );

        // Then
        assertEquals(HttpStatus.OK, response.statusCode);
        assertEquals("Title 1", response.body?.title);
    }
}