package shop.rememberspring.rememberspringkt.member.domain

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import shop.rememberspring.rememberspringkt.global.entity.BaseEntity

@Entity
class Member (
    val email: String,

    val name: String,

    val picture: String,

    @Enumerated(EnumType.STRING)
    val role: Role

): BaseEntity()