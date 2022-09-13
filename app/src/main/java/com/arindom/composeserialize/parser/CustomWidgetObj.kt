package com.arindom.composeserialize.parser

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class CustomWidgetObj(
    override val schema: Schema,
    override val data: CustomViewElement
) : WidgetObj(), ViewElement<CustomWidgetObj.CustomViewElement> {

    @Serializable
    data class CustomViewElement(
        val text: String,
        val buttonText: String
    )

    override fun getCompose(): Compose {
        return Compose.Success { eventProcessor ->
            var text by remember { mutableStateOf("") }
            val coroutineScope = rememberCoroutineScope()
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(text = data.text)
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    coroutineScope.launch {
                        eventProcessor?.emit("Entered $text")
                    }
                }) {
                    Text(text = data.buttonText)
                }
            }
        }
    }
}
