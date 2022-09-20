package com.arindom.composeserialize.presentation.screens.screentwo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arindom.composeserialize.data.ScreenRepo
import com.arindom.composeserialize.presentation.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ScreenTwoViewModel:ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    private val screenRepo = ScreenRepo()
    val uiState = _uiState.asStateFlow()

    fun fetchMainScreen(){
        viewModelScope.launch {
            screenRepo.getMainScreen("screen_two.json")
                .onEach {widgetObj ->
                    _uiState.value = _uiState.value.copy(
                        data = widgetObj
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect()
        }
    }
}