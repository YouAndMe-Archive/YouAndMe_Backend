package shop.rememberspring.rememberspringkt.global.jwt.api.dto

data class TokenDto(
    val accessToken: String,
    val refreshToken: String
)
