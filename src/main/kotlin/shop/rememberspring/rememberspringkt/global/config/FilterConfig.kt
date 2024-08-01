package shop.rememberspring.rememberspringkt.global.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import shop.rememberspring.rememberspringkt.global.jwt.JwtAuthorizationFilter
import shop.rememberspring.rememberspringkt.global.jwt.TokenProvider

@Configuration
class FilterConfig(
    private val tokenProvider: TokenProvider
) {

    @Bean
    fun jwtFilterRegistrationBean(): FilterRegistrationBean<JwtAuthorizationFilter> =
        FilterRegistrationBean<JwtAuthorizationFilter>().apply {
            filter = JwtAuthorizationFilter(tokenProvider)
            addUrlPatterns(
                "/api/v1/user/diaries/*",
                "/api/v1/user/members/*"
            )
        }
}