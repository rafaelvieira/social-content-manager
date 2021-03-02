package com.rafalabs.socialcm.content

import com.rafalabs.socialcm.content.domain.Content
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class ContentTest {

    @Test
    internal fun should_CreateContent_When_Properties_Filled() {
        val expectedTitle = "Learning Kotlin by using TDD";
        val expectedDescription = "How to learn Kotlin through TDD process";
        val expectedValue = "Some big content";

        val content =
            Content.Builder(expectedTitle)
                .description(expectedDescription)
                .value(expectedValue)
                .build();

        assertEquals(content.title, expectedTitle);
        assertEquals(content.description, expectedDescription);
        assertEquals(content.value, expectedValue);
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