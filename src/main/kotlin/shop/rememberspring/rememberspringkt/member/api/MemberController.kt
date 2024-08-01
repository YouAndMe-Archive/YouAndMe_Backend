package shop.rememberspring.rememberspringkt.member.api

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shop.rememberspring.rememberspringkt.global.annotation.CurrentUserEmail
import shop.rememberspring.rememberspringkt.global.template.RspTemplate
import shop.rememberspring.rememberspringkt.member.api.dto.response.MemberInfoResDto
import shop.rememberspring.rememberspringkt.member.application.MemberService

@RestController
@RequestMapping("/api/v1/user/members")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/info")
    fun info(@CurrentUserEmail email: String): RspTemplate<MemberInfoResDto> {
        return RspTemplate(HttpStatus.OK, "사용자 정보 불러오기", memberService.info(email))
    }

}