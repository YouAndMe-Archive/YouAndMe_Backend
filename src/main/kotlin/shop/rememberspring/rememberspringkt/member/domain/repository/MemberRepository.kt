package shop.rememberspring.rememberspringkt.member.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import shop.rememberspring.rememberspringkt.member.domain.Member

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByEmail(email: String): Member?
}