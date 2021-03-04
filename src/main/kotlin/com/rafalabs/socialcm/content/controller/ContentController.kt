package com.rafalabs.socialcm.content.controller

import com.rafalabs.socialcm.content.domain.Content
import com.rafalabs.socialcm.content.service.ContentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController("/api/content")
class ContentController {
    @Autowired
    private lateinit var contentService: ContentService;

    constructor(contentService: ContentService) {
        this.contentService = contentService;
    }

    fun create(content: Content): ResponseEntity<Content> {
        return ResponseEntity.ok(
            contentService.create(content));
    }

    fun update(content: Content): ResponseEntity<Content> {
        return ResponseEntity.ok(
            contentService.update(content));
    }

    fun findById(id: Long): ResponseEntity<Content> {
        return ResponseEntity.ok(
            contentService.findById(id));
    }
}