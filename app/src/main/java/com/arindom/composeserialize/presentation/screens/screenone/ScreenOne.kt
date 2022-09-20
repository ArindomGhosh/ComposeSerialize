package com.arindom.composeserialize.presentation.screens.screenone

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arindom.composeserialize.presentation.widgets.CommonRootWidget

@Composable
fun ScreenOne(
    path:String,
    modifier: Modifier = Modifier,
    mainViewModel: ScreenOneViewModel = viewModel(),
) {
    mainViewModel.fetchMainScreen(path)
    val uiState by mainViewModel.uiState.collectAsState()
    CommonRootWidget(
        uiState = uiState
    )
}


/**
 * Type of Events
 * 1) API call
 * 2) Navigation
 * 3) ...
 * */