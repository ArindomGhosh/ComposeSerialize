package com.arindom.composeserialize.presentation.screens.screenone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arindom.composeserialize.data.ScreenRepo
import com.arindom.composeserialize.presentation.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ScreenOneViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    private val screenRepo = ScreenRepo()
    val uiState = _uiState.asStateFlow()

    fun fetchMainScreen(path:String){
        viewModelScope.launch {
            screenRepo.getMainScreen(path)//"res/raw/screen_one.json"
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