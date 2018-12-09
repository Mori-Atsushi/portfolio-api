package atsushi.work.api.controllers

import atsushi.work.api.entities.ErrorResponse
import atsushi.work.api.helper.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity

@ControllerAdvice
class BadRequestExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    fun getNotFoundException(req: HttpServletRequest, e: NotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("not found"), HttpStatus.NOT_FOUND)
    }
}