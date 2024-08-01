package shop.rememberspring.rememberspringkt.global.jwt

import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.GenericFilterBean
import shop.rememberspring.rememberspringkt.global.util.JwtAuthConstants.AUTHORIZATION_HEADER
import shop.rememberspring.rememberspringkt.global.util.JwtAuthConstants.BEARER_PREFIX

class JwtAuthorizationFilter(
    private val tokenProvider: TokenProvider
) : GenericFilterBean() {

    override fun doFilter(servletRequest: ServletRequest?, servletResponse: ServletResponse?, chain: FilterChain) {
        val request = servletRequest as? HttpServletRequest ?: return
        val response = servletResponse as? HttpServletResponse ?: return

        try {
            val header = request.getHeader(AUTHORIZATION_HEADER)
            if (header.startsWith(BEARER_PREFIX)) {
                val token = header.removePrefix(BEARER_PREFIX)
                if (!tokenProvider.validateToken(token)) {
                    sendCustomError(response, HttpServletResponse.SC_UNAUTHORIZED, "토큰이 유효하지 않습니다.")
                    return
                }

                request.setAttribute("email", header?.let {
                    tokenProvider.getAuthenticationEmail(
                        token
                    )
                })
            }

            chain.doFilter(request, response)
        } catch (e: ExpiredJwtException) {
            sendCustomError(response, HttpServletResponse.SC_UNAUTHORIZED, "accessToken이 만료되었습니다.")
        } catch (e: Exception) {
            sendCustomError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 필터 에러입니다.")
        }
    }

    private fun sendCustomError(response: HttpServletResponse, statusCode: Int, message: String) {
        response.status = statusCode
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        val errorJson = """{"error": "$message"}"""
        response.writer.write(errorJson)
        response.writer.flush()
    }
}