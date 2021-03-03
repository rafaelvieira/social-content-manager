package com.rafalabs.socialcm.content.repository.entity

import com.rafalabs.socialcm.content.domain.Content
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "content")
class ContentEntity {

    @Column(name = "id")
    var id:             Long? = null;

    @Column(name = "title")
    var title:          String = "";

    @Column(name = "description")
    var description:    String = "";

    @Column(name = "value")
    var value:          String = "";

    companion object {
        fun from(domain: Content): ContentEntity {

            val entity = ContentEntity();
            entity.id           = domain.id;
            entity.title        = domain.title;
            entity.description  = domain.description;
            entity.value        = domain.value;

            return entity;
        }

        fun to(entity: ContentEntity): Content {

            return Content.Builder(entity.title)
                    .id(entity.id)
                    .description(entity.description)
                    .value(entity.value)
                    .build();
        }
    }
}
