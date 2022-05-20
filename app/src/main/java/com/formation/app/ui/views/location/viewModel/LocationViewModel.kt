package com.formation.app.ui.views.location.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.app.models.Location
import com.formation.app.services.appData.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val userService: UserService,
) : ViewModel() {

    private val _locationsFound: MutableStateFlow<List<Location>> = MutableStateFlow(emptyList())
    val locationsFound: StateFlow<List<Location>> = _locationsFound.asStateFlow()

    private val _name: MutableStateFlow<String> = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _isSearch: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSearch: StateFlow<Boolean> = _isSearch.asStateFlow()

    fun fetchLocations() {
        viewModelScope.launch {
            _locationsFound.value = userService.locations.value
        }
    }

    fun setName(name: String) {
        _name.value = name
    }

    fun searchByName() {
        _isSearch.value = true
        _locationsFound.value = userService.locations.value.filter {
            it.name.uppercase().contains(_name.value.uppercase())
        }
    }
}