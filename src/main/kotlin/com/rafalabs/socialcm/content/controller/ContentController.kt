package com.rafalabs.socialcm.content.controller

import com.rafalabs.socialcm.content.controller.dto.ContentDTO
import com.rafalabs.socialcm.content.service.ContentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping(ContentController.BASE_URL)
class ContentController {

    @Autowired
    private lateinit var contentService: ContentService;

    @PostMapping
    fun create(@RequestBody content: ContentDTO): ResponseEntity<Unit> {
        val persistedContent = contentService.create(ContentDTO.to(content));
        return ResponseEntity
                .created(buildLinkTo(persistedContent.id?:0L))
                .build();
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long,
               @RequestBody content: ContentDTO): ResponseEntity<Unit> {
        contentService.update(id, ContentDTO.to(content));
        return ResponseEntity
                    .noContent()
                    .build();
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<ContentDTO> {
        val dto = ContentDTO.from(
            contentService.findById(id)
        );
        return ResponseEntity.ok(dto);
    }

    private fun buildLinkTo(id: Long): URI {
        return URI("""${ContentController.BASE_URL}/${id}""");
    }

    companion object {
        internal const val BASE_URL = "/api/content";
    }
}