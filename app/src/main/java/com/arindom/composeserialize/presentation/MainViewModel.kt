package com.arindom.composeserialize.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arindom.composeserialize.data.ScreenRepo
import com.arindom.composeserialize.parser.DefaultWidget
import com.arindom.composeserialize.parser.WidgetObj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class UiState(
    val isLoading: Boolean = false,
    val error: Exception? = null,
    val data: WidgetObj = DefaultWidget()
)

class MainViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    private val screenRepo = ScreenRepo()
    val uiState = _uiState.asStateFlow()

    fun fetchMainScreen(){
        viewModelScope.launch {
            screenRepo.getMainScreen()
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