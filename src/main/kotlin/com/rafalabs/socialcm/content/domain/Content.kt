package com.rafalabs.socialcm.content.domain

import java.lang.IllegalArgumentException

class Content {
    val id: Long?;
    val title: String;
    val description: String;
    val value: String;

    private constructor(id: Long?, title: String, description: String = "", value: String = "") {
        this.id = id;
        this.title =
            if(title.isBlank())
                throw IllegalArgumentException("Title cannot be blank")
            else title;
        this.description = description.trim();
        this.value = value.trim();
    }

    data class Builder(val title: String) {
        private var id: Long? = null;
        private var description: String = "";
        private var value: String = "";

        fun id(id: Long?) = apply { this.id = id }
        fun description(description: String) = apply { this.description = description }
        fun value(value: String) = apply { this.value = value }
        fun build() = Content(id, title, description, value);
    }
}
