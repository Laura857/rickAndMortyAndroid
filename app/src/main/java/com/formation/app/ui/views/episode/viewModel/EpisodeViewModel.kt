package com.formation.app.ui.views.episode.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.app.models.Episode
import com.formation.app.services.appData.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val userService: UserService,
) : ViewModel() {

    private val _episodesFound: MutableStateFlow<List<Episode>> = MutableStateFlow(emptyList())
    val episodesFound: StateFlow<List<Episode>> = _episodesFound.asStateFlow()

    private val _name: MutableStateFlow<String> = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _isSearch: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSearch: StateFlow<Boolean> = _isSearch.asStateFlow()

    fun fetchEpisodes() {
        viewModelScope.launch {
            _episodesFound.value = userService.episodes.value
        }
    }

    fun setName(name: String) {
        _name.value = name
    }

    fun searchByName() {
        _isSearch.value = true
        _episodesFound.value = userService.episodes.value.filter {
            it.name.uppercase().contains(_name.value.uppercase())
        }
    }
}