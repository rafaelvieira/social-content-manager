package com.rafalabs.socialcm.exception

import org.springframework.http.HttpStatus

class InternalErrorException : ApplicationException {
    constructor(
        message: String,
        cause: Throwable? = null
    ) : super(
        code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
        message,
        cause
    )
}