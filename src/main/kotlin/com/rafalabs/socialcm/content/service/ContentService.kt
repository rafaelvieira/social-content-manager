package com.rafalabs.socialcm.content.service

import com.rafalabs.socialcm.content.domain.Content
import org.springframework.stereotype.Service
import com.rafalabs.socialcm.content.repository.ContentRepository
import com.rafalabs.socialcm.content.repository.entity.ContentEntity
import org.springframework.beans.factory.annotation.Autowired

@Service
class ContentService {
    @Autowired
    private lateinit var contentRepository: ContentRepository;

    fun save(content: Content): Content {
        val newContentEntity = contentRepository.save(ContentEntity.fromDomain(content));
        return ContentEntity.toDomain(newContentEntity);
    }
}