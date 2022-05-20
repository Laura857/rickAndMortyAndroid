package com.formation.app.ui.views.composable.viewModels

import androidx.lifecycle.ViewModel
import com.formation.app.services.appData.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComposableViewModel @Inject constructor(private val userService: UserService) : ViewModel() {
    val authState = userService.authState
}