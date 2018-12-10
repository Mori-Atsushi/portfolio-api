package atsushi.work.api.datasources.db

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

object CategoriesTable: Table() {
    val id = CategoriesTable.integer("id").autoIncrement().primaryKey()
    val name = CategoriesTable.text("name")
}

@Component
class Category(
        config: Config
) {
    init {
        config.setup()
        transaction {
            SchemaUtils.create(CategoriesTable)
        }
    }
}
