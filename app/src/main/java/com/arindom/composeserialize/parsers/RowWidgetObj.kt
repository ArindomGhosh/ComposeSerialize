package com.arindom.composeserialize.parsers

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Serializable
data class RowWidgetObj(
    override val schema: Schema,
    override val data: ViewElementSL.MultiElement,
) : WidgetObj(), ViewGroup {
    override fun getCompose(): Compose {
        return Compose.Success {
            Row(
                modifier = Modifier
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                data.elements.forEach { widgetObj ->
                    widgetObj.getCompose().Render()
                    Spacer(modifier = Modifier.padding(end = 8.dp))
                }
            }
        }
    }
}