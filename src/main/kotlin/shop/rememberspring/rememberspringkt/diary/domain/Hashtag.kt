package shop.rememberspring.rememberspringkt.diary.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import shop.rememberspring.rememberspringkt.global.entity.BaseEntity

@Entity
class Hashtag(
    var name: String? = null,

    @OneToMany(mappedBy = "hashtag", cascade = [CascadeType.ALL], orphanRemoval = true)
    val diaryHashtagMapping: Set<DiaryHashtagMapping> = HashSet()
) : BaseEntity() {
}