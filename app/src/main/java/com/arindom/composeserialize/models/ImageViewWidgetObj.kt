package com.arindom.composeserialize.models

import androidx.compose.material.Text
import com.arindom.composeserialize.parser.Compose
import com.arindom.composeserialize.parser.Schema
import com.arindom.composeserialize.parser.ViewElement
import com.arindom.composeserialize.parser.WidgetObj
import kotlinx.serialization.Serializable

@Serializable
data class ImageViewWidgetObj(
    override val schema: Schema,
    override val data: ImageViewElement,
) : WidgetObj(), ViewElement<ImageViewWidgetObj.ImageViewElement> {
    @Serializable
    data class ImageViewElement(
        val imageUrl: String
    )

    override fun getCompose(): Compose {
        return Compose.Success { Text(text = "load image from ${data.imageUrl}") }
    }
}
