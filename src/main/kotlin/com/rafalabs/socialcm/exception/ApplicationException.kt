package com.rafalabs.socialcm.exception

open class ApplicationException : Exception {
    val code: Int

    constructor(
        code: Int,
        message: String,
        cause: Throwable? = null
    ) : super(
        message,
        cause
    ) {
        this.code = code;
    }

}