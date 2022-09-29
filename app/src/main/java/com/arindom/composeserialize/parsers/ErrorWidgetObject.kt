package com.arindom.composeserialize.parsers

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

internal val errorSchema = Schema(
    type = "Error",
    version = "1.0.0"
)

@Serializable
data class ErrorWidgetObject(
    val data: Map<String, JsonElement>,
    override val schema: Schema = errorSchema
) : WidgetObj() {
    override fun getCompose(): Compose {
        return Compose.Error(Exception("no widget definition found for $data"))
    }
}