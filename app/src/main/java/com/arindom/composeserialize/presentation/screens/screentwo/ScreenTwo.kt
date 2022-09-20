package com.arindom.composeserialize.presentation.screens.screentwo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arindom.composeserialize.presentation.widgets.CommonRootWidget

@Composable
fun ScreenTwo(
    modifier: Modifier = Modifier,
    screenTwoViewModel: ScreenTwoViewModel = viewModel()
) {
    screenTwoViewModel.fetchMainScreen()
    val uiState by screenTwoViewModel.uiState.collectAsState()
    CommonRootWidget(
        uiState = uiState
    )
}