package shop.rememberspring.rememberspringkt.auth.exception

import shop.rememberspring.rememberspringkt.global.error.exception.NotFoundGroupException

class TokenNotFoundException : NotFoundGroupException {
    constructor(message: String) : super(message)
    constructor() : this("존재하지 않는 토큰입니다.")
}