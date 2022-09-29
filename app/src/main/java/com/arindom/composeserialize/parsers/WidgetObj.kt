package com.arindom.composeserialize.parsers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(with = WidgetObjectSerializer::class)
abstract class WidgetObj : ViewBuilder, Metadata

@Serializable
data class DefaultWidget(
    override val schema: Schema = Schema(
        type = "Default",
        version = "1.0.0"
    )
) : WidgetObj() {
    override fun getCompose(): Compose {
        return Compose.Success {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Default Ui")
            }
        }
    }
}

interface Metadata {
    val schema: Schema
}

interface ViewGroup {
    val data: ViewElementSL
}

interface ViewElement<ViewType> {
    val data: ViewType
}

sealed interface ViewElementSL {
    @Serializable
    data class MultiElement(
        val elements: List<WidgetObj>
    ):ViewElementSL

    @Serializable
    data class SingleElement(
        val element: WidgetObj
    ):ViewElementSL
}

@Serializable
data class Schema(
    val type: String,
    val version: String
)

@PublishedApi
internal object WidgetObjManager {
    private val widgetObjSerializerMap: MutableMap<String, KSerializer<out WidgetObj>> =
        mutableMapOf()

    fun registerWidget(widgetObjPair: Pair<String, KSerializer<out WidgetObj>>) {
        require(widgetObjSerializerMap[widgetObjPair.first] == null) {
            "${widgetObjPair.first} widget already exist."
        }
        widgetObjSerializerMap[widgetObjPair.first] = widgetObjPair.second
    }

    fun getSerializer(viewType: String): KSerializer<out WidgetObj> =
        widgetObjSerializerMap[viewType]
            ?: throw IllegalArgumentException("No Serializer found for $viewType")
}

fun registerWidgetObjManager(vararg widgetObjs: Pair<String, KSerializer<out WidgetObj>>) {
    widgetObjs.forEach { widgetObj ->
        WidgetObjManager.registerWidget(widgetObj)
    }
}

object WidgetObjectSerializer : JsonContentPolymorphicSerializer<WidgetObj>(WidgetObj::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out WidgetObj> {
        val viewType = element.jsonObject["schema"]?.jsonObject?.get("type")?.jsonPrimitive?.content
        require(viewType != null) {
            throw IllegalArgumentException("No type found!!")
        }
        return try {
            WidgetObjManager.getSerializer(viewType)
        } catch (exception: Exception) {
            return ErrorWidgetObject.serializer()
        }
    }
}