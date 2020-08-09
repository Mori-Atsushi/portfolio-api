package atsushi.work.api

import org.springframework.util.StringUtils
import org.springframework.web.reactive.config.ResourceHandlerRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer

class SwaggerUiWebFluxConfigurer(
    private val baseUrl: String
) : WebFluxConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        val baseUrl: String = StringUtils.trimTrailingCharacter(baseUrl, '/')
        registry.addResourceHandler("$baseUrl/swagger-ui/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
            .resourceChain(false)
    }
}
