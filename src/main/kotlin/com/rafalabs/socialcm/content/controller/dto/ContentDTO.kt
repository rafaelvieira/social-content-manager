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

            val dto = ContentDTO();
            dto.id           = domain.id;
            dto.title        = domain.title;
            dto.description  = domain.description;
            dto.value        = domain.value;

            return dto;
        }

        fun to(dto: ContentDTO): Content {

            return Content.Builder(dto.title)
                    .id(dto.id)
                    .description(dto.description)
                    .value(dto.value)
                    .build();
        }
    }
}
