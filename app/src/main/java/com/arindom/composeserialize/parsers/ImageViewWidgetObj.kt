package com.arindom.composeserialize.parsers

import androidx.compose.material.Text
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
