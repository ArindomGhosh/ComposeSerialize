package com.arindom.composeserialize.presentation.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arindom.composeserialize.parsers.Render
import com.arindom.composeserialize.presentation.UiState

@Composable
fun CommonRootWidget(
    modifier: Modifier = Modifier,
    uiState: UiState
) {
    uiState
        .data
        .getCompose()
        .Render()
}