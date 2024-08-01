package shop.rememberspring.rememberspringkt.member.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import shop.rememberspring.rememberspringkt.member.api.dto.response.MemberInfoResDto
import shop.rememberspring.rememberspringkt.member.domain.repository.MemberRepository
import shop.rememberspring.rememberspringkt.member.exception.MemberNotFoundException

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun info(email: String?): MemberInfoResDto? {
        val member = email?.let { memberRepository.findByEmail(it) ?: throw MemberNotFoundException() }

        return member?.let { MemberInfoResDto(member.email, member.name, member.picture) }
    }
}