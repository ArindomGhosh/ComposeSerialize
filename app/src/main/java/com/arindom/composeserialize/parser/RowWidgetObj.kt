package com.arindom.composeserialize.parser

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Serializable
data class RowWidgetObj(
    override val schema: Schema,
    override val data: ViewElements,
) : WidgetObj(), ViewGroup {
    override fun getCompose(): Compose {
        return Compose.Success {
            Row() {
                data.elements.forEach { widgetObj ->
                    widgetObj.getCompose().Render()
                    Spacer(modifier = Modifier.padding(end = 8.dp))
                }
            }
        }
    }
}