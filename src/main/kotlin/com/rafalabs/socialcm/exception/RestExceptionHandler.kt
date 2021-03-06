package com.rafalabs.socialcm.exception

import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.HttpStatus

import org.springframework.web.context.request.WebRequest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class RestExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [ApplicationException::class])
    protected fun handleGenericError(
        ex: ApplicationException?,
        request: WebRequest?
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(
                if(ex == null) HttpStatus.INTERNAL_SERVER_ERROR
                else HttpStatus.valueOf(ex.code))
            .header("error_message", ex?.message)
            .build();
    }

    @ExceptionHandler(value = [Exception::class])
    protected fun handleGenericError(
        ex: Exception?,
        request: WebRequest?
    ): ResponseEntity<Unit> {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("error_message", "Error while submitting request.")
                .build();
    }
}