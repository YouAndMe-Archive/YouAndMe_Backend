package shop.rememberspring.rememberspringkt.auth.api.response

import shop.rememberspring.rememberspringkt.member.domain.Member


data class MemberLoginResDto(
    val findMember: Member
)
