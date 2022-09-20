package com.arindom.composeserialize.parser

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arindom.composeserialize.presentation.routes.LocalAmpNavigation
import com.arindom.composeserialize.presentation.uriScreen
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
        return Compose.Success {
            var text by remember { mutableStateOf("") }
            val coroutineScope = rememberCoroutineScope()
            val ampNavigation = LocalAmpNavigation.current
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
                    ampNavigation.navigateToUri("$uriScreen/screen_two.json")
                }) {
                    Text(text = data.buttonText)
                }
            }
        }
    }
}
