package com.rafalabs.socialcm.content.service

import com.rafalabs.socialcm.content.domain.Content
import org.springframework.stereotype.Service
import com.rafalabs.socialcm.content.repository.ContentRepository

@Service
class ContentService {
    private val contentRepository: ContentRepository;

    fun save(content: Content): Content {
        contentRepository.add(
                Content.Builder(content.title)
                    .id(1)
                    .description(content.description)
                    .value(content.value)
                    .build());
        return contentRepository.get(0);
    }
}