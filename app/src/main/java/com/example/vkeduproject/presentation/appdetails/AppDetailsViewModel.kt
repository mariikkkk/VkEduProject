package com.example.vkeduproject.presentation.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkeduproject.data.appdetails.AppDetailsApi
import com.example.vkeduproject.data.appdetails.AppDetailsMapper
import com.example.vkeduproject.data.appdetails.CategoryMapper
import com.example.vkeduproject.domain.appdetails.GetAppDetailsUseCase
import com.example.vkeduproject.domain.appdetails.ObserveAppDetailsUseCase
import com.example.vkeduproject.domain.appdetails.ToggleWishlistUseCase
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds


@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val appDetailsUseCase: GetAppDetailsUseCase,
    private val observeAppDetailsUseCase: ObserveAppDetailsUseCase,
    private val toggleWishlistUseCase: ToggleWishlistUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private  val _events = Channel<AppDetailsEvent>(BUFFERED)
    val events = _events.receiveAsFlow()
    private val id: String = checkNotNull(savedStateHandle["id"])

    init {
        getAppDetails()
        observeAppDetails()
    }

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
    fun getAppDetails(){
        viewModelScope.launch {
            _state.value = AppDetailsState.Loading

            runCatching {
                appDetailsUseCase(id)

            }.onFailure {
                _state.value = AppDetailsState.Error
            }
        }
    }

    private fun observeAppDetails() {
        viewModelScope.launch {
            observeAppDetailsUseCase(id).catch { _state.value = AppDetailsState.Error }
                .collect { appDetails ->
                    _state.value = AppDetailsState.Content(
                        appDetails = appDetails,
                        descriptionCollapsed = false,
                    )
                }
        }
    }
    fun toggleWishlist() {
        viewModelScope.launch {
            toggleWishlistUseCase(id)
        }
    }
}