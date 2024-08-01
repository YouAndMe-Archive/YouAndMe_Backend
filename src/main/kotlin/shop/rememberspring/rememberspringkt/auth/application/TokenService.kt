package shop.rememberspring.rememberspringkt.auth.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import shop.rememberspring.rememberspringkt.auth.api.request.RefreshTokenReqDto
import shop.rememberspring.rememberspringkt.auth.api.response.MemberLoginResDto
import shop.rememberspring.rememberspringkt.auth.exception.InvalidTokenException
import shop.rememberspring.rememberspringkt.auth.exception.TokenNotFoundException
import shop.rememberspring.rememberspringkt.global.jwt.TokenProvider
import shop.rememberspring.rememberspringkt.global.jwt.api.dto.TokenDto
import shop.rememberspring.rememberspringkt.global.jwt.domain.Token
import shop.rememberspring.rememberspringkt.global.jwt.domain.repository.TokenRepository
import shop.rememberspring.rememberspringkt.member.domain.repository.MemberRepository
import shop.rememberspring.rememberspringkt.member.exception.MemberNotFoundException

@Service
@Transactional(readOnly = true)
class TokenService(
    private val tokenRepository: TokenRepository,
    private val tokenProvider: TokenProvider,
    private val memberRepository: MemberRepository
) {
    @Transactional
    fun getToken(memberLoginResDto: MemberLoginResDto): TokenDto {
        val tokenDto = tokenProvider.generateToken(memberLoginResDto.findMember.email)

        tokenSaveAndUpdate(memberLoginResDto, tokenDto)

        return tokenDto
    }

    fun tokenSaveAndUpdate(memberLoginResDto: MemberLoginResDto, tokenDto: TokenDto) {
        if (!tokenRepository.existsByMember(memberLoginResDto.findMember)) {
            tokenRepository.save(Token(tokenDto.refreshToken, memberLoginResDto.findMember))
        }

        refreshTokenUpdate(memberLoginResDto, tokenDto)
    }

    private fun refreshTokenUpdate(memberLoginResDto: MemberLoginResDto, tokenDto: TokenDto) {
        val token = tokenRepository.findByMember(memberLoginResDto.findMember).orElseThrow { TokenNotFoundException() }
        token.refreshTokenUpdate(tokenDto.refreshToken)
    }

    @Transactional
    fun generateAccessToken(refreshTokenDto: RefreshTokenReqDto): TokenDto {
        if (!tokenRepository.existsByRefreshToken(refreshTokenDto.refreshToken) || !tokenProvider.validateToken(
                refreshTokenDto.refreshToken
            )
        ) {
            throw InvalidTokenException()
        }

        val token =
            tokenRepository.findByRefreshToken(refreshTokenDto.refreshToken).orElseThrow { TokenNotFoundException() }
        token.member.id?.let { id ->
            val member = memberRepository.findById(id).orElseThrow { MemberNotFoundException() }

            return tokenProvider.generateAccessTokenByRefreshToken(member.email, token.refreshToken)
        } ?: throw MemberNotFoundException()
    }

}