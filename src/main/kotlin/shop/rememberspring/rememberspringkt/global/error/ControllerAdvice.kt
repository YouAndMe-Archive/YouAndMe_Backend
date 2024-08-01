package shop.rememberspring.rememberspringkt.global.error

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import shop.rememberspring.rememberspringkt.global.error.dto.ErrorResponse
import shop.rememberspring.rememberspringkt.global.error.exception.AccessDeniedGroupException
import shop.rememberspring.rememberspringkt.global.error.exception.AuthGroupException
import shop.rememberspring.rememberspringkt.global.error.exception.InvalidGroupException
import shop.rememberspring.rememberspringkt.global.error.exception.NotFoundGroupException
import shop.rememberspring.rememberspringkt.global.template.RspTemplate
import java.util.*

@RestControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundGroupException::class)
    fun handleNotFoundDate(e: RuntimeException): RspTemplate<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.NOT_FOUND.value(), e.message.toString())

        return RspTemplate(statusCode = HttpStatus.NOT_FOUND, data = errorResponse)
    }

    @ExceptionHandler(AuthGroupException::class)
    fun handleAuthDate(e: RuntimeException): RspTemplate<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.message.toString())

        return RspTemplate(statusCode = HttpStatus.BAD_REQUEST, data = errorResponse)
    }

    @ExceptionHandler(AccessDeniedGroupException::class)
    fun handleAccessDeniedDate(e: RuntimeException): RspTemplate<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.FORBIDDEN.value(), e.message.toString())

        return RspTemplate(statusCode = HttpStatus.FORBIDDEN, data = errorResponse)
    }

    @ExceptionHandler(InvalidGroupException::class)
    fun handleInvalidDate(e: RuntimeException): RspTemplate<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.message.toString())

        return RspTemplate(statusCode = HttpStatus.BAD_REQUEST, data = errorResponse)
    }

    // Validation 관련 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val fieldError = Objects.requireNonNull(e.fieldError)
        val errorResponse = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            String.format("%s. (%s)", fieldError!!.defaultMessage, fieldError.field)
        )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}