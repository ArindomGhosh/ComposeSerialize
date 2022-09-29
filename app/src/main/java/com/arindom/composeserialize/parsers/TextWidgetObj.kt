package com.arindom.composeserialize.parsers

import androidx.compose.material.Text
import kotlinx.serialization.Serializable

@Serializable
data class TextWidgetObj(
    override val schema: Schema,
    override val data: TextViewElement,
) : WidgetObj(), ViewElement<TextWidgetObj.TextViewElement> {

    @Serializable
    data class TextViewElement(
        val text: String
    )

    override fun getCompose(): Compose {
        return Compose.Success { Text(data.text) }
    }
}