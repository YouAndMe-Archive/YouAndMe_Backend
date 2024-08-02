package shop.rememberspring.rememberspringkt.image.domain

import jakarta.persistence.*
import shop.rememberspring.rememberspringkt.diary.domain.Diary
import shop.rememberspring.rememberspringkt.global.entity.BaseEntity
import shop.rememberspring.rememberspringkt.member.domain.Member

@Entity
class Image(
    var convertImageUrl: String,

    val imageSequence: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private val member: Member?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "diary_id")
    private val diary: Diary?

) : BaseEntity() {
}