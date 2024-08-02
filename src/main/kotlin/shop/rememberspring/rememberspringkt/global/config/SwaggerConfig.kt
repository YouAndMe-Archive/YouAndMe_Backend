package shop.rememberspring.rememberspringkt.global.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig(
    @Value("\${myapp.api-url}")
    private val prodUrl: String,

    @Value("\${myapp.local-url}")
    private val localUrl: String
) {
    @Bean
    fun openAPI(): OpenAPI {
        val info = Info()
            .title("Remember-spring API Document")
            .version("v1.0.1")
            .description("Remember-spring 문서")

        val authName = "Json Web Token"

        val securityRequirement = SecurityRequirement().addList(authName)
        val components = Components()
            .addSecuritySchemes(
                authName,
                SecurityScheme()
                    .name(authName)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("Bearer")
                    .bearerFormat("JWT")
                    .description("Access Token 토큰을 입력해주세요.(Bearer 붙이지 않아도 됩니다.)")
            )

        val prodServer = Server()
        prodServer.description("Production Server")
            .url(prodUrl)

        val localServer = Server()
        localServer.description("Development Server")
            .url(localUrl)

        return OpenAPI()
            .addSecurityItem(securityRequirement)
            .components(components)
            .info(info)
            .servers(listOf(prodServer, localServer))
    }
}