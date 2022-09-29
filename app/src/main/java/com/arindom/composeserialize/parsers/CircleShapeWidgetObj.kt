package com.arindom.composeserialize.parsers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Serializable
data class CircleShapeWidgetObj(
    override val schema: Schema,
    override val data: CircleType
) : WidgetObj(), ViewElement<CircleShapeWidgetObj.CircleType> {
    @Serializable
    data class CircleType(
        val size: Int
    )

    override fun getCompose(): Compose {
        return Compose.Success {
            Column(
                modifier = Modifier
                    .padding(16.dp )
                    .wrapContentSize(Alignment.Center),
            ) {
                Box(
                    modifier = Modifier
                        .size(data.size.dp)
                        .clip(CircleShape)
                        .background(Color.Red)
                )
            }
        }
    }
}