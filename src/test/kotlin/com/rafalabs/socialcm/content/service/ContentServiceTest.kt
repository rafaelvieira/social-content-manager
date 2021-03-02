package com.rafalabs.socialcm.content.service

import com.rafalabs.socialcm.content.domain.Content
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContentServiceTest {

    private lateinit var contentService: ContentService;

    @BeforeAll
    internal fun setUp() {
        contentService = ContentService();
    }

    @Test
    internal fun should_CreateContent_When_Properties_Filled() {
        val expectedContent =
            Content.Builder("Learning Kotlin by using TDD")
                .description("How to learn Kotlin through TDD process")
                .value("Some big content")
                .build();

        val persistedContent = contentService.save(expectedContent);

        assertNotNull(persistedContent.id);
        assertEquals(persistedContent.title, expectedContent.title);
        assertEquals(persistedContent.description, expectedContent.description);
        assertEquals(persistedContent.value, expectedContent.value);
    }

    @Test
    internal fun should_CreateContent_When_Optional_Properties_Not_Filled() {
        val content =
                Content.Builder("Learning Kotlin by using TDD")
                        .description(" ")
                        .value(" ")
                        .build();

        assertEquals(content.description, "")
        assertEquals(content.value, "")
    }

    @Test
    internal fun should_ThrowException_When_TitleIsBlank() {

        assertThrows(IllegalArgumentException::class.java) {
            Content.Builder(" ")
                    .description("How to learn Kotlin through TDD process")
                    .value("Some big content")
                    .build();
        };
    }
}