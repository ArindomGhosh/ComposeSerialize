package com.arindom.composeserialize.parsers

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Serializable
data class ColumnWidgetObj(
    override val schema: Schema,
    override val data: ViewElementSL.MultiElement
) : WidgetObj(), ViewGroup {
    override fun getCompose(): Compose {
        return Compose.Success {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                data.elements.forEach { widgetObj ->
                    widgetObj.getCompose().Render()
                    Spacer(modifier = Modifier.padding(bottom = 8.dp))
                }
            }
        }

    }
}