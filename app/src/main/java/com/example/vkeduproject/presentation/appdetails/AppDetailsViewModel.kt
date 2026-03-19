package com.example.vkeduproject.presentation.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkeduproject.data.appdetails.AppDetailsApi
import com.example.vkeduproject.data.appdetails.AppDetailsMapper
import com.example.vkeduproject.data.appdetails.AppDetailsMockRepositoryImpl
import com.example.vkeduproject.data.appdetails.CategoryMapper
import com.example.vkeduproject.domain.appdetails.GetAppDetailsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class AppDetailsViewModel : ViewModel(){
    private val appDetailsUseCase = GetAppDetailsUseCase(
        AppDetailsMockRepositoryImpl(
            mapper = AppDetailsMapper(categoryMapper = CategoryMapper()),
            api = AppDetailsApi()
        )
    )
    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private  val _events = Channel<AppDetailsEvent>(BUFFERED)
    val events = _events.receiveAsFlow()

    fun showUnderDevelopmentMessage() {
        viewModelScope.launch {
            _events.send(AppDetailsEvent.UnderDevelopment)
        }
    }

    fun collapseDescription(){
        _state.update { currentState ->
            if(currentState is AppDetailsState.Content){
                currentState.copy(descriptionCollapsed = true)
            }else{
                currentState
            }
        }
    }
    fun getAppDetails(appId: String){
        viewModelScope.launch {
            _state.value = AppDetailsState.Loading

            runCatching {
                val appDetails = appDetailsUseCase(appId)

                _state.value = AppDetailsState.Content(
                    appDetails = appDetails,
                    descriptionCollapsed = false,
                )
            }.onFailure {
                _state.value = AppDetailsState.Error
            }
        }
    }
}