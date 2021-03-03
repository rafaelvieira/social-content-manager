package com.rafalabs.socialcm.content.repository.entity

import com.rafalabs.socialcm.content.domain.Content
import javax.persistence.Entity

@Entity(name = "content")
class ContentEntity {
    var id: Long? = null;
    var title: String = "";
    var description: String = "";
    var value: String = "";

    companion object {
        fun fromDomain(domain: Content): ContentEntity {

            val entity = ContentEntity();
            entity.id           = domain.id;
            entity.title        = domain.title;
            entity.description  = domain.description;
            entity.value        = domain.value;

            return entity;
        }

        fun toDomain(entity: ContentEntity): Content {

            return Content.Builder(entity.title)
                    .id(entity.id)
                    .description(entity.description)
                    .value(entity.value)
                    .build();
        }
    }
}
