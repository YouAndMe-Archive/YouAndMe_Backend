package shop.rememberspring.rememberspringkt.auth.application

import shop.rememberspring.rememberspringkt.auth.api.response.UserInfo

interface AuthService {
    fun getUserInfo(idToken: String): UserInfo
}