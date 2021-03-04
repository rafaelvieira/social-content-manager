package com.rafalabs.socialcm.content.controller

import com.rafalabs.socialcm.content.domain.Content
import com.rafalabs.socialcm.content.service.ContentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController("/api/content")
class ContentController {
    @Autowired
    private lateinit var contentService: ContentService;

    constructor(contentService: ContentService) {
        this.contentService = contentService;
    }

    @PostMapping
    fun create(@RequestBody content: Content): ResponseEntity<Content> {
        return ResponseEntity.ok(
            contentService.create(content));
    }

    @PutMapping("/{id}")
    fun update(@PathParam("id") id: Long,
               @RequestBody content: Content): ResponseEntity<Content> {
        return ResponseEntity.ok(
            contentService.update(id, content));
    }

    @GetMapping("/{id}")
    fun findById(@PathParam("id") id: Long): ResponseEntity<Content> {
        return ResponseEntity.ok(
            contentService.findById(id));
    }
}