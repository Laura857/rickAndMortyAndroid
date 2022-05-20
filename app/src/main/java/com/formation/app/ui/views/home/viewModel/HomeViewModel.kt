package com.formation.app.ui.views.home.viewModel

import androidx.lifecycle.ViewModel
import com.formation.app.models.Personage
import com.formation.app.services.appData.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userService: UserService,
) : ViewModel() {

    private val _personagesFound: MutableStateFlow<List<Personage>> = MutableStateFlow(emptyList())
    val personagesFound: StateFlow<List<Personage>> = _personagesFound.asStateFlow()


    private val _name: MutableStateFlow<String> = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _isSearch: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSearch: StateFlow<Boolean> = _isSearch.asStateFlow()

    fun fetchPersonages() {
        _personagesFound.value = userService.personages.value
    }

    fun setName(name: String) {
        _name.value = name
    }

    fun searchByName() {
        _isSearch.value = true
        _personagesFound.value = userService.personages.value.filter {
            it.name.uppercase().contains(_name.value.uppercase())
        }
    }
}