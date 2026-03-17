package com.example.vkeduproject.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkeduproject.domain.appdetails.AppDetails
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

data class AppListState (
    val items: List<AppDetails> = emptyList()
)

sealed interface ScreenEvent {
    data class ShowSnackbar(val message: String) : ScreenEvent
}

class AppListViewModel: ViewModel(){
    private val _state = MutableStateFlow(AppListState())
    val state: StateFlow<AppListState> = _state.asStateFlow()

    private val _events = Channel<ScreenEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init{
        _state.value = _state.value.copy(
            items = mockAppsList
        )
    }
    fun onLogoClick(){
        viewModelScope.launch {
            _events.send(
                ScreenEvent.ShowSnackbar("Вы кликнули на лого RuStore! Пасхалка!")
            )
        }
    }

}