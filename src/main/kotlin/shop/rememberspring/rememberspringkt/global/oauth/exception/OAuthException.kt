package shop.rememberspring.rememberspringkt.global.oauth.exception

import shop.rememberspring.rememberspringkt.global.error.exception.AuthGroupException

class OAuthException : AuthGroupException {
    constructor(message: String) : super(message)
    constructor() : this("OAuth 서버와의 통신 과정에서 문제가 발생했습니다.")
}