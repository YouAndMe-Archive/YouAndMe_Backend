package shop.rememberspring.rememberspringkt.global.jwt.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import shop.rememberspring.rememberspringkt.global.jwt.domain.Token
import shop.rememberspring.rememberspringkt.member.domain.Member
import java.util.*

interface TokenRepository : JpaRepository<Token, Long> {
    fun existsByMember(member: Member): Boolean
    fun findByMember(member: Member): Optional<Token>
    fun existsByRefreshToken(refreshToken: String): Boolean
    fun findByRefreshToken(refreshToken: String): Optional<Token>
}