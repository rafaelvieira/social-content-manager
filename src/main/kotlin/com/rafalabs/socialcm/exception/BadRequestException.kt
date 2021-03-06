package com.rafalabs.socialcm.exception

import org.springframework.http.HttpStatus

class BadRequestException : ApplicationException {
    constructor(
        message: String,
        cause: Throwable? = null
    ) : super(
        code = HttpStatus.BAD_REQUEST.value(),
        message,
        cause
    )

}