package com.arindom.composeserialize.presentation

import com.arindom.composeserialize.parser.DefaultWidget
import com.arindom.composeserialize.parser.WidgetObj

data class UiState(
    val isLoading: Boolean = false,
    val error: Exception? = null,
    val data: WidgetObj = DefaultWidget()
)