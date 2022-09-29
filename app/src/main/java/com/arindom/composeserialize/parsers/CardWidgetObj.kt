package com.arindom.composeserialize.parsers

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Serializable
data class CardWidgetObj(
    override val schema: Schema,
    override val data: ViewElementSL.SingleElement
) : ViewGroup, WidgetObj() {
    override fun getCompose(): Compose {
        return Compose.Success {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                data.element.getCompose().Render()
            }
        }
    }
}