package shop.rememberspring.rememberspringkt.diary.domain

import jakarta.persistence.*
import shop.rememberspring.rememberspringkt.global.entity.BaseEntity
import shop.rememberspring.rememberspringkt.member.domain.Member

@Entity
class Diary(
    val title: String,
    val isPublic: Boolean,
    val diaryType: DiaryType,
    val emotion: Emotion,
    val voiceText: String,
    val content: String,
    val likeCount: Int,

    @OneToMany(mappedBy = "diary", cascade = [CascadeType.ALL], orphanRemoval = true)
    val diaryHashtagMapping: Set<DiaryHashtagMapping> = HashSet(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member,
) : BaseEntity() {
}