package shop.rememberspring.rememberspringkt.diary.application

import org.springframework.stereotype.Service
import shop.rememberspring.rememberspringkt.diary.domain.repository.DiaryRepository

@Service
class DiaryService(
    private val diaryRepository: DiaryRepository
) {
}