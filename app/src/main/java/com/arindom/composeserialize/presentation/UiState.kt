package com.arindom.composeserialize.presentation

import com.arindom.composeserialize.parsers.DefaultWidget
import com.arindom.composeserialize.parsers.WidgetObj

data class UiState(
    val isLoading: Boolean = false,
    val error: Exception? = null,
    val data: WidgetObj = DefaultWidget()
)