package com.rafalabs.socialcm.content.domain

import java.lang.IllegalArgumentException

class Content {
    val title: String
    val description: String
    val value: String

    private constructor(title: String, description: String = "", value: String = "") {
        this.title =
            if(title.isBlank())
                throw IllegalArgumentException("Title cannot be blank")
            else title
        this.description = description.trim()
        this.value = value.trim()
    }

    data class Builder(val title: String) {
        private var description: String = ""
        private var value: String = ""

        fun description(description: String) = apply { this.description = description }
        fun value(value: String) = apply { this.value = value }
        fun build() = Content(title, description, value);
    }
}
