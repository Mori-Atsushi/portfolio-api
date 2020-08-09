package atsushi.work.api.controller.util

import atsushi.work.api.controller.response.ErrorResponse
import atsushi.work.api.model.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@ControllerAdvice
class BadRequestExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    fun getNotFoundException(e: NotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("not found"), HttpStatus.NOT_FOUND)
    }
}
