package shop.rememberspring.rememberspringkt.auth.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import shop.rememberspring.rememberspringkt.auth.api.response.MemberLoginResDto
import shop.rememberspring.rememberspringkt.auth.api.response.UserInfo
import shop.rememberspring.rememberspringkt.member.domain.Member
import shop.rememberspring.rememberspringkt.member.domain.Role
import shop.rememberspring.rememberspringkt.member.domain.repository.MemberRepository

@Service
@Transactional(readOnly = true)
class AuthMemberService(
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun saveUserInfo(userInfo: UserInfo): MemberLoginResDto {
        val member = memberRepository.findByEmail(userInfo.email) ?: createMember(userInfo)

        return MemberLoginResDto(member)
    }

    private fun createMember(userInfo: UserInfo): Member {
        return memberRepository.save(
            Member(
                userInfo.email,
                userInfo.nickname,
                userInfo.picture,
                Role.ROLE_USER
            )
        )
    }

}