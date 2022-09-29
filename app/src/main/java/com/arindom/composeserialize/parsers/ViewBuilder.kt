package com.arindom.composeserialize.parsers

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

interface ViewBuilder {
    fun getCompose(): Compose
}

sealed class Compose {
    data class Success(val buildView: @Composable () -> Unit) :
        Compose()

    data class Error(val exception: Exception) : Compose()
}

@Composable
fun Compose.Render() {
    when (this) {
        is Compose.Error -> Text(text = this.exception.message ?: "")
        is Compose.Success -> this.buildView.invoke()
    }
}

