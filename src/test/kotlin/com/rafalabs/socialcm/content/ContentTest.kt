package com.rafalabs.socialcm.content

import com.rafalabs.socialcm.content.domain.Content
import org.junit.jupiter.api.Test

class ContentTest {

    @Test
    internal fun testCreate() {
        val content =
            Content.Builder("Learning Kotlin by using TDD")
                .description("How to learn Kotlin through TDD process")
                .value("Some big content")
                .build();
    }
}