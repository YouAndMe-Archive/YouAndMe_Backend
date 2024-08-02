package shop.rememberspring.rememberspringkt.image.api

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import shop.rememberspring.rememberspringkt.global.annotation.CurrentUserEmail
import shop.rememberspring.rememberspringkt.global.template.RspTemplate
import shop.rememberspring.rememberspringkt.image.api.dto.response.ImageResDto
import shop.rememberspring.rememberspringkt.image.application.ImageService
import java.io.IOException

@RestController
@RequestMapping("/api/v1/user")
class ImageController(
    private val imageService: ImageService
) {
    @PostMapping("/upload/url")
    @Throws(IOException::class)
    fun imageURLUpload(
        @CurrentUserEmail email: String?,
        @RequestBody imageUrls: List<String>
    ): RspTemplate<List<ImageResDto>> {
        return RspTemplate(HttpStatus.OK, "이미지 업로드", imageService.urlUpload(email, imageUrls))
    }

    @GetMapping("/images")
    fun images(@CurrentUserEmail email: String?): RspTemplate<List<ImageResDto>> {
        val images: List<ImageResDto>? = imageService.imagesAll(email)

        return RspTemplate(HttpStatus.OK, "이미지 조회", images)
    }

}