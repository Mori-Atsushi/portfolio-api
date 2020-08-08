package atsushi.work.api.controllers.util

import atsushi.work.api.controllers.response.ErrorResponse
import atsushi.work.api.model.exception.NotFoundException
import javax.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@ControllerAdvice
class BadRequestExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    fun getNotFoundException(req: HttpServletRequest, e: NotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("not found"), HttpStatus.NOT_FOUND)
    }
}
