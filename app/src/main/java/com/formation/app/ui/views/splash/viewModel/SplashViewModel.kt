package com.formation.app.ui.views.splash.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.app.services.appData.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val userService: UserService) : ViewModel() {
    fun fetchSplashData() {
        viewModelScope.launch {
            userService.fetchSplashData()
        }
    }
}