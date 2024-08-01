package shop.rememberspring.rememberspringkt.diary.domain

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import shop.rememberspring.rememberspringkt.global.entity.BaseEntity

@Entity
class DiaryHashtagMapping(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    var diary: Diary,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    val hashtag: Hashtag
) : BaseEntity() {
}