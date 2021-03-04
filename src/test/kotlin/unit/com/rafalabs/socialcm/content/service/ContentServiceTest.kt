package unit.com.rafalabs.socialcm.content.service

import com.rafalabs.socialcm.content.domain.Content
import com.rafalabs.socialcm.content.repository.ContentRepository
import com.rafalabs.socialcm.content.repository.entity.ContentEntity
import com.rafalabs.socialcm.content.service.ContentService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension::class)
class ContentServiceTest {

    @InjectMocks
    private lateinit var contentService: ContentService;

    @Mock
    private lateinit var contentRepository: ContentRepository;

    @Test
    internal fun should_CreateContent_When_Properties_Filled() {
        // Given
        val expectedContent =
            Content.Builder("Learning Kotlin by using TDD")
                .description("How to learn Kotlin through TDD process")
                .value("Some big content")
                .build();

        val expectedContentEntity = ContentEntity();
        expectedContentEntity.id = 1;
        expectedContentEntity.title = expectedContent.title;
        expectedContentEntity.description = expectedContent.description;
        expectedContentEntity.value = expectedContent.value;

        doReturn(expectedContentEntity)
            .`when`(contentRepository)
            .save(any());

        // When
        val persistedContent = contentService.save(expectedContent);

        // Then
        assertEquals(1,                     persistedContent.id);
        assertEquals(expectedContent.title,         persistedContent.title);
        assertEquals(expectedContent.description,   persistedContent.description);
        assertEquals(expectedContent.value,         persistedContent.value);
    }

    @Test
    internal fun should_CreateContent_When_Optional_Properties_Not_Filled() {
        // Given
        val expectedContent =
            Content.Builder("Learning Kotlin by using TDD")
                .description("   ")
                .value("   ")
                .build();

        val expectedContentEntity = ContentEntity();
        expectedContentEntity.id = 1;
        expectedContentEntity.title = expectedContent.title;
        expectedContentEntity.description = expectedContent.description;
        expectedContentEntity.value = expectedContent.value;

        doReturn(expectedContentEntity)
            .`when`(contentRepository)
            .save(any());

        // When
        val persistedContent = contentService.save(expectedContent);

        // Then
        assertEquals(1,                    persistedContent.id);
        assertEquals(expectedContent.title,         persistedContent.title);
        assertEquals(expectedContent.description,   persistedContent.description);
        assertEquals(expectedContent.value,         persistedContent.value);
    }

    @Test
    internal fun should_ThrowErrorCreatingContent_When_TitleIsBlank() {

        assertThrows(IllegalArgumentException::class.java, {
            Content.Builder("   ")
                .build();
        }, "Title cannot be blank");
    }

    @Test
    internal fun should_FindContent_When_IdExists() {
        // Given
        val expectedContentEntity = ContentEntity();
        expectedContentEntity.id = 1;
        expectedContentEntity.title = "Learning Kotlin by using TDD";

        doReturn(Optional.of(expectedContentEntity))
            .`when`(contentRepository)
            .findById(1);

        // When
        val content = contentService.findById(1);

        // Then
        assertEquals(expectedContentEntity.title, content.title);
    }

    @Test
    internal fun should_ThrowErrorFindingContent_When_IdDoesNotExists() {
        // Given
        val nonExistentId = 2L;

        // When... Then
        assertThrows(Exception::class.java, {
            val content = contentService.findById(nonExistentId);
        }, "Content ID 2 not found");
    }
}