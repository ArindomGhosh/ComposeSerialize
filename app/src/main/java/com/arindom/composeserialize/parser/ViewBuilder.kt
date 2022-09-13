package com.arindom.composeserialize.parser

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlin.reflect.KClass

interface ViewBuilder {
    fun getCompose(): Compose
}

sealed class Compose {
    data class Success(val buildView: @Composable (eventProcessor: MutableSharedFlow<Any>?) -> Unit) :
        Compose()

    data class Error(val exception: Exception) : Compose()
}

@Composable
fun Compose.Render(eventProcessor: MutableSharedFlow<Any>?) {
    when (this) {
        is Compose.Error -> Text(text = this.exception.message ?: "")
        is Compose.Success -> this.buildView.invoke(eventProcessor)
    }
}

