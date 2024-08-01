package shop.rememberspring.rememberspringkt.global.jwt.domain

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import shop.rememberspring.rememberspringkt.global.entity.BaseEntity
import shop.rememberspring.rememberspringkt.member.domain.Member

@Entity
class Token(
    var refreshToken: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member,
) : BaseEntity() {
    fun refreshTokenUpdate(refreshToken: String) {
        if (this.refreshToken != refreshToken) {
            this.refreshToken = refreshToken;
        }
    }
}