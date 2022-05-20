package com.formation.app.ui.views.episodeDetails.viewDetails

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
class EpisodeDetailsViewModel @Inject constructor(
    private val appService: AppService,
) : ViewModel() {

    private val _episode: MutableStateFlow<Episode?> = MutableStateFlow(null)
    val episode: StateFlow<Episode?> = _episode.asStateFlow()

    private val _personages: MutableStateFlow<List<Personage>> = MutableStateFlow(emptyList())
    val personages: StateFlow<List<Personage>> = _personages.asStateFlow()

    fun fetchEpisodes(id: String) {
        viewModelScope.launch {
            _episode.value = appService.getEpisodeById(id)
            val allEpisodesPersonagesId: List<String> =
                _episode.value?.characters?.map { personage -> personage.substringAfter("character/") }
                    ?: emptyList()
            println("allEpisodesPersonagesId $allEpisodesPersonagesId")
            viewModelScope.launch {
                _personages.value =
                    appService.getAllPersonagesByIds(allEpisodesPersonagesId) ?: emptyList()
            }
        }
    }
}