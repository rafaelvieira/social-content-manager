package com.rafalabs.socialcm.content.domain

class Content private constructor(
        val title: String,
        val description: String = "",
        val value: String = "") {

    data class Builder(val title: String) {
        private var description: String = ""
        private var value: String = ""

        fun description(description: String) = apply { this.description = description }
        fun value(value: String) = apply { this.value = value }
        fun build() = Content(title, description, value);
    }
}
