package com.rafalabs.socialcm.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.HttpStatus

import org.springframework.web.context.request.WebRequest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class RestExceptionHandler: ResponseEntityExceptionHandler() {

    private val LOGGER = LoggerFactory.getLogger(RestExceptionHandler::class.java)

    @ExceptionHandler(value = [ApplicationException::class])
    protected fun handleApplicationError(
        ex: ApplicationException?,
        request: WebRequest?
    ): ResponseEntity<Unit> {
        val httpStatus =
            if (ex == null) HttpStatus.INTERNAL_SERVER_ERROR
            else HttpStatus.valueOf(ex.code);

        if(httpStatus == HttpStatus.INTERNAL_SERVER_ERROR)
            LOGGER.error(ex?.message);

        return ResponseEntity
            .status(httpStatus)
            .header("error_message", ex?.message)
            .build();
    }

    @ExceptionHandler(value = [Exception::class])
    protected fun handleGenericError(
        ex: Exception?,
        request: WebRequest?
    ): ResponseEntity<Unit> {
        LOGGER.error(ex?.message);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("error_message", "Error while submitting request.")
                .build();
    }
}