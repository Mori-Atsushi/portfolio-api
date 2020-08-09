package atsushi.work.api

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun configure(http: ServerHttpSecurity): SecurityWebFilterChain {

        // CORS設定
        http.cors()
            .configurationSource(corsConfigurationSource())

        // CSRF設定
        http.csrf().disable()

        return http.build()
    }

    /**
     * CORS設定
     *
     * @return CORS設定
     */
    private fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL)
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL)
        corsConfiguration.addAllowedOrigin("https://at-sushi.work")
        corsConfiguration.addAllowedOrigin("https://api.at-sushi.work")
        corsConfiguration.addAllowedOrigin("http://localhost:3000")
        corsConfiguration.allowCredentials = true

        val corsConfigurationSource = UrlBasedCorsConfigurationSource()
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration)

        return corsConfigurationSource
    }
}
