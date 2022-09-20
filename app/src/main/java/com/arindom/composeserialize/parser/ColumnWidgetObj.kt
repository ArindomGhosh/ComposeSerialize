package com.arindom.composeserialize.parser

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Serializable
data class ColumnWidgetObj(
    override val schema: Schema,
    override val data: ViewElements
) : WidgetObj(), ViewGroup {
    override fun getCompose(): Compose {
        return Compose.Success {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                data.elements.forEach { widgetObj ->
                    widgetObj.getCompose().Render()
                    Spacer(modifier = Modifier.padding(bottom = 8.dp))
                }
            }
        }

    }
}