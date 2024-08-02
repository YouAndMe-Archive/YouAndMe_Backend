package shop.rememberspring.rememberspringkt.image.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import shop.rememberspring.rememberspringkt.image.api.dto.response.ImageResDto
import shop.rememberspring.rememberspringkt.image.domain.Image
import shop.rememberspring.rememberspringkt.image.domain.repository.ImageRepository
import shop.rememberspring.rememberspringkt.member.domain.Member
import shop.rememberspring.rememberspringkt.member.domain.repository.MemberRepository
import shop.rememberspring.rememberspringkt.member.exception.MemberNotFoundException

@Service
@Transactional(readOnly = true)
class ImageService(
    private val imageRepository: ImageRepository,
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun urlUpload(email: String?, imageUrls: List<String>): List<ImageResDto> {
        val member = email?.let { memberRepository.findByEmail(it) ?: throw MemberNotFoundException() }

        val responseImages: MutableList<ImageResDto> = ArrayList()
        var imageSequence = 1

        for (imageUrl in imageUrls) {
            val image = imageSave(imageUrl, imageSequence, member)
            responseImages.add(ImageResDto(image.convertImageUrl, image.imageSequence))

            imageSequence++
        }

        return responseImages
    }

    private fun imageSave(image: String, imageSequence: Int, member: Member?): Image {
        return imageRepository.save(Image(image, imageSequence, member, null))
    }

    fun imagesAll(email: String?): List<ImageResDto>? {
        val member = email?.let { memberRepository.findByEmail(it) ?: throw MemberNotFoundException() }

        return member?.let { getImagesResDto(it) }
    }

    private fun getImagesResDto(member: Member): List<ImageResDto> {
        val getImages: List<Image> = imageRepository.findByMember(member)
        val responseImages: MutableList<ImageResDto> = java.util.ArrayList()

        for (image in getImages) {
            responseImages.add(ImageResDto(image.convertImageUrl, image.imageSequence))
        }

        return responseImages
    }

}