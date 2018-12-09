package atsushi.work.api.datasources.db


import org.jetbrains.exposed.sql.Database
import org.springframework.stereotype.Component

@Component
class Config(
        private val properties: Properties
) {
    private var database: Database? = null

    fun setup() {
        if(database == null) {
            database = Database.connect(
                    properties.fullUrl,
                    properties.driverClassName,
                    properties.username,
                    properties.password
            )
        }
    }
}