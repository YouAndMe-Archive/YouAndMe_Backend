package shop.rememberspring.rememberspringkt.global.template

import org.springframework.http.HttpStatus

data class RspTemplate<T>(
    var statusCode: HttpStatus,
    var message: String? = null,
    var data: T? = null
)