package shop.rememberspring.rememberspringkt.diary.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import shop.rememberspring.rememberspringkt.diary.domain.Diary

interface DiaryRepository : JpaRepository<Diary, Long> {
}