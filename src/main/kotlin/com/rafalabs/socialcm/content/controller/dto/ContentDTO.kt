package com.rafalabs.socialcm.content.controller.dto

import com.rafalabs.socialcm.content.domain.Content
import java.io.Serializable
import javax.persistence.*

class ContentDTO: Serializable {

    var id:             Long? = null;
    var title:          String = "";
    var description:    String = "";
    var value:          String = "";

    companion object {
        fun from(domain: Content): ContentDTO {

            val entity = ContentDTO();
            entity.id           = domain.id;
            entity.title        = domain.title;
            entity.description  = domain.description;
            entity.value        = domain.value;

            return entity;
        }

        fun to(entity: ContentDTO): Content {

            return Content.Builder(entity.title)
                    .id(entity.id)
                    .description(entity.description)
                    .value(entity.value)
                    .build();
        }
    }
}
