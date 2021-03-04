package com.rafalabs.socialcm.content

import com.rafalabs.socialcm.content.controller.ContentController
import com.rafalabs.socialcm.content.controller.dto.ContentDTO
import com.rafalabs.socialcm.content.domain.Content
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ContentControllerTest {

    @Autowired
    private lateinit var restClient: TestRestTemplate;

//    @Test
//    internal fun should_CreateContent_When_Properties_Filled() {
//        // Given
//        val expectedContent =
//            Content.Builder("Learning Kotlin by using TDD")
//                .description("How to learn Kotlin through TDD process")
//                .value("Some big content")
//                .build();
//
//        val expectedContentEntity = ContentEntity();
//        expectedContentEntity.id = 1L;
//        expectedContentEntity.title = expectedContent.title;
//        expectedContentEntity.description = expectedContent.description;
//        expectedContentEntity.value = expectedContent.value;
//
//        doReturn(expectedContentEntity)
//            .`when`(contentRepository)
//            .save(any());
//
//        // When
//        val persistedContent = contentService.create(expectedContent);
//
//            // Then
//        assertEquals(1,                     persistedContent.id);
//        assertEquals(expectedContent.title,         persistedContent.title);
//        assertEquals(expectedContent.description,   persistedContent.description);
//        assertEquals(expectedContent.value,         persistedContent.value);
//    }
//
//    @Test
//    internal fun should_UpdateContent_When_Properties_Filled() {
//        // Given
//        val expectedContent =
//            Content.Builder("Learning Kotlin by using TDD 2")
//                .id(1)
//                .description("How to learn Kotlin through TDD process 2")
//                .value("Some big content 2")
//                .build();
//
//        val expectedContentEntity = ContentEntity();
//        expectedContentEntity.id            = expectedContent.id;
//        expectedContentEntity.title         = expectedContent.title;
//        expectedContentEntity.description   = expectedContent.description;
//        expectedContentEntity.value         = expectedContent.value;
//
//        doReturn(expectedContentEntity)
//            .`when`(contentRepository)
//            .save(any());
//
//        // When
//        val persistedContent = contentService.update(expectedContentEntity.id!!, expectedContent);
//
//        // Then
//        assertEquals(expectedContent.id,            persistedContent.id);
//        assertEquals(expectedContent.title,         persistedContent.title);
//        assertEquals(expectedContent.description,   persistedContent.description);
//        assertEquals(expectedContent.value,         persistedContent.value);
//    }

    @Test
    internal fun should_FindContent_When_IdExists() {
        // Given
        val id = 1L;

        // When
        val content: ResponseEntity<ContentDTO> =
            restClient
                .getForEntity(
                    """${ContentController.BASE_URL}/${id}""",
                    ContentDTO::class
                );

        // Then
        assertEquals("Title 1", content.body?.title);
    }
}