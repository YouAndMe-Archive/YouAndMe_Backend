package shop.rememberspring.rememberspringkt.image.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import shop.rememberspring.rememberspringkt.image.domain.Image
import shop.rememberspring.rememberspringkt.member.domain.Member

interface ImageRepository : JpaRepository<Image, Long> {
    fun findByMember(member: Member): List<Image>
}