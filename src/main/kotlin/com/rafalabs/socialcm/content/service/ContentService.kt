package com.rafalabs.socialcm.content.service

import com.rafalabs.socialcm.content.domain.Content
import org.springframework.stereotype.Service
import com.rafalabs.socialcm.content.repository.ContentRepository
import com.rafalabs.socialcm.content.repository.entity.ContentEntity
import com.rafalabs.socialcm.exception.BadRequestException
import org.springframework.beans.factory.annotation.Autowired

@Service
class ContentService {
    @Autowired
    private lateinit var contentRepository: ContentRepository;

    fun create(content: Content): Content {
        val newContentEntity = contentRepository.save(ContentEntity.fromWithoutId(content));
        return ContentEntity.to(newContentEntity);
    }

    fun update(id: Long, content: Content): Content {
        val newContentEntity = contentRepository.save(ContentEntity.from(id, content));
        return ContentEntity.to(newContentEntity);
    }

    fun findById(id: Long): Content {
        return ContentEntity.to(
                    contentRepository
                        .findById(id)
                        .orElseThrow { BadRequestException("""Content with ID $id not found.""") });
    }
}