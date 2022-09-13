package com.arindom.composeserialize.parser

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    widgets: Compose
) {
    val eventProcessor = MutableSharedFlow<Any>()
    val eventProcessor2 = MutableSharedFlow<MainEvents>()
    LaunchedEffect(key1 = true) {
        eventProcessor.collect {
            println(it)
        }
    }
    Box(modifier = modifier) {
        widgets.Render(eventProcessor)
    }
}

sealed class MainEvents {
    data class Navigate(val path: String) : MainEvents()
    data class ApiEvent(val params: Array<String>) : MainEvents()
}

/**
 * Type of Events
 * 1) API call
 * 2) Navigation
 * 3) ...
 * */