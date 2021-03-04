package com.rafalabs.socialcm.content.service

import com.rafalabs.socialcm.content.domain.Content
import org.springframework.stereotype.Service
import com.rafalabs.socialcm.content.repository.ContentRepository
import com.rafalabs.socialcm.content.repository.entity.ContentEntity
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@Service
class ContentService {
    @Autowired
    private lateinit var contentRepository: ContentRepository;

    fun create(content: Content): Content {
        val newContentEntity = contentRepository.save(ContentEntity.fromWithoutId(content));
        return ContentEntity.to(newContentEntity);
    }

    fun update(content: Content): Content {
        if(content.id == null) throw IllegalArgumentException("Content ID cannot be null");
        val newContentEntity = contentRepository.save(ContentEntity.from(content));
        return ContentEntity.to(newContentEntity);
    }

    fun findById(id: Long): Content {
        return ContentEntity.to(
                    contentRepository
                        .findById(id)
                        .orElseThrow { Exception("""Content with ID $id not found.""") });
    }
}