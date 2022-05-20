package com.formation.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    fun fetchAll(onCreate: Boolean) {
        viewModelScope.launch {
            try {
            } catch (E: Exception) {

            }
        }
    }


}