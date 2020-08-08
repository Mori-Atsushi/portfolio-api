package atsushi.work.api.datasource.db.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "database")
class Properties {
    lateinit var url: String
    lateinit var username: String
    lateinit var password: String
    lateinit var driverClassName: String
    val fullUrl: String get() = "$url?useUnicode=true&useFastDateParsing=false&characterEncoding=UTF-8"
}
