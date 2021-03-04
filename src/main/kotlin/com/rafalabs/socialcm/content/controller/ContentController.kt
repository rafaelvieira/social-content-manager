package com.rafalabs.socialcm.content.controller

import com.rafalabs.socialcm.content.domain.Content
import com.rafalabs.socialcm.content.service.ContentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import javax.websocket.server.PathParam

@RestController(ContentController.BASE_URL)
class ContentController {

    @Autowired
    private lateinit var contentService: ContentService;

    constructor(contentService: ContentService) {
        this.contentService = contentService;
    }

    @PostMapping
    fun create(@RequestBody content: Content): ResponseEntity<Unit> {
        val persistedContent = contentService.create(content);
        return ResponseEntity
                .created(buildLinkTo(persistedContent.id?:0L))
                .build();
    }

    @PutMapping("/{id}")
    fun update(@PathParam("id") id: Long,
               @RequestBody content: Content): ResponseEntity<Unit> {
        contentService.update(id, content);
        return ResponseEntity
                    .noContent()
                    .build();
    }

    @GetMapping("/{id}")
    fun findById(@PathParam("id") id: Long): ResponseEntity<Content> {
        return ResponseEntity.ok(
            contentService.findById(id));
    }

    private fun buildLinkTo(id: Long): URI {
        return URI("""${ContentController.BASE_URL}/${id}""");
    }

    companion object {
        internal const val BASE_URL = "/api/content";
    }
}