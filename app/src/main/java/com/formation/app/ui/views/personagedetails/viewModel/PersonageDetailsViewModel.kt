package com.formation.app.ui.views.personagedetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.app.common.api.AppService
import com.formation.app.models.Episode
import com.formation.app.models.Personage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonageDetailsViewModel @Inject constructor(
    private val appService: AppService,
) : ViewModel() {

    private val _personage: MutableStateFlow<Personage?> = MutableStateFlow(null)
    val personage: StateFlow<Personage?> = _personage.asStateFlow()

    private val _episodes: MutableStateFlow<List<Episode>> = MutableStateFlow(emptyList())
    val episodes: StateFlow<List<Episode>> = _episodes.asStateFlow()

    fun fetchPersonage(id: String) {
        viewModelScope.launch {
            _personage.value = appService.getPersonageById(id)
            println("_personage.value ${_personage.value}")
            val allPersonageEpisodesId: List<String> =
                _personage.value?.episode?.map { it.substringAfter("episode/") }
                    ?: emptyList()
            println("allPersonageEpisodesId $allPersonageEpisodesId")
            viewModelScope.launch {
                _episodes.value =
                    appService.getAllEpisodesByIds(allPersonageEpisodesId) ?: emptyList()
            }
        }
    }
}