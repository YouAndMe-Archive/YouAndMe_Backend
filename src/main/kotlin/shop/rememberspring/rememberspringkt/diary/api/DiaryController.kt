package shop.rememberspring.rememberspringkt.diary.api

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shop.rememberspring.rememberspringkt.diary.application.DiaryService
import shop.rememberspring.rememberspringkt.global.template.RspTemplate

@RestController
@RequestMapping("/api/v1/user/diaries")
class DiaryController(
    private val diaryService: DiaryService
) {

    @PostMapping("")
    fun test(): RspTemplate<String> {
        return RspTemplate(HttpStatus.OK, "test")
    }
}