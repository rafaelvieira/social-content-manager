package unit.com.rafalabs.socialcm.content.service

import com.rafalabs.socialcm.content.controller.ContentController
import com.rafalabs.socialcm.content.domain.Content
import com.rafalabs.socialcm.content.repository.ContentRepository
import com.rafalabs.socialcm.content.repository.entity.ContentEntity
import com.rafalabs.socialcm.content.service.ContentService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.lang.AssertionError
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension::class)
class ContentControllerTest {

    @InjectMocks
    private lateinit var contentController: ContentController;

    @InjectMocks
    private lateinit var contentService: ContentService;

    @Mock
    private lateinit var contentRepository: ContentRepository;

    @BeforeAll
    internal fun setUp() {
        contentService = ContentService();
        contentController = ContentController(contentService);
    }

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
        val response = contentController.create(expectedContent);
        val persistedContent =
            if(response.body == null) throw AssertionError("Response body cannot be null")
            else response.body!!;

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
        val response = contentController.create(expectedContent);
        val persistedContent =
            if(response.body == null) throw AssertionError("Response body cannot be null")
            else response.body!!;

        // Then
        assertEquals(1,                    persistedContent.id);
        assertEquals(expectedContent.title,         persistedContent.title);
        assertEquals(expectedContent.description,   persistedContent.description);
        assertEquals(expectedContent.value,         persistedContent.value);
    }

    @Test
    internal fun should_ThrowErrorCreatingContent_When_TitleIsBlank() {

        val exception = assertThrows(IllegalArgumentException::class.java) {
            Content.Builder("   ")
                .build();
        };
        assertEquals("Title cannot be blank", exception.message);
    }

    @Test
    internal fun should_UpdateContent_When_Properties_Filled() {
        // Given
        val expectedContent =
            Content.Builder("Learning Kotlin by using TDD 2")
                .id(1)
                .description("How to learn Kotlin through TDD process 2")
                .value("Some big content 2")
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
        val response = contentController.update(expectedContent);
        val persistedContent =
            if(response.body == null) throw AssertionError("Response body cannot be null")
            else response.body!!;

        // Then
        assertEquals(1,                     persistedContent.id);
        assertEquals(expectedContent.title,         persistedContent.title);
        assertEquals(expectedContent.description,   persistedContent.description);
        assertEquals(expectedContent.value,         persistedContent.value);
    }

    @Test
    internal fun should_ThrowErrorUpdatingContent_When_IdIsNull() {
        // Given
        val content =
            Content.Builder("Anything")
                .build();

        val exception = assertThrows(IllegalArgumentException::class.java) {
            // When
            contentController.update(content);
        };
        assertEquals("Content ID cannot be null", exception.message);
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
        val response = contentController.findById(1);
        val content =
            if(response.body == null) throw AssertionError("Response body cannot be null")
            else response.body!!;

        // Then
        assertEquals(expectedContentEntity.title, content.title);
    }

    @Test
    internal fun should_ThrowErrorFindingContent_When_IdDoesNotExists() {
        // Given
        val nonExistentId = 2L;

        // When... Then
        val exception = assertThrows(Exception::class.java) {
            contentController.findById(nonExistentId);
        }
        assertEquals("Content with ID 2 not found.", exception.message);
    }
}