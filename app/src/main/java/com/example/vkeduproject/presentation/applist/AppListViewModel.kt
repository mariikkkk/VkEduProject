package com.example.vkeduproject.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkeduproject.data.appdetails.AppDetailsMapper
import com.example.vkeduproject.data.appdetails.CategoryMapper
import com.example.vkeduproject.data.applist.AppListApi
import com.example.vkeduproject.data.applist.AppListRepositoryImpl
import com.example.vkeduproject.domain.appdetails.AppDetails
import com.example.vkeduproject.domain.applist.GetAppListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
    class AppListViewModel @Inject constructor(
    private val appListUseCase: GetAppListUseCase
): ViewModel(){
    private val _state = MutableStateFlow(AppListState())
    val state: StateFlow<AppListState> = _state.asStateFlow()

    private val _events = Channel<ScreenEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init{
        getAppList()
    }

    fun getAppList(){
        viewModelScope.launch {
            val appList = appListUseCase()
            _state.value = _state.value.copy(items = appList)
        }
    }
    fun onLogoClick(){
        viewModelScope.launch {
            _events.send(
                ScreenEvent.ShowSnackbar("Вы кликнули на лого RuStore! Пасхалка!")
            )
        }
    }

}