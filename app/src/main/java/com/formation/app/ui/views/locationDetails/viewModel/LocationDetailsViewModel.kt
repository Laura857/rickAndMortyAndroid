package com.formation.app.ui.views.locationDetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.app.common.api.AppService
import com.formation.app.models.Location
import com.formation.app.models.Personage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailsViewModel @Inject constructor(
    private val appService: AppService,
) : ViewModel() {

    private val _location: MutableStateFlow<Location?> = MutableStateFlow(null)
    val location: StateFlow<Location?> = _location.asStateFlow()

    private val _residents: MutableStateFlow<List<Personage>> = MutableStateFlow(emptyList())
    val residents: StateFlow<List<Personage>> = _residents.asStateFlow()

    fun fetchLocation(id: String) {
        viewModelScope.launch {
            _location.value = appService.getLocationById(id)
            val allLocationResidentsId: List<String> =
                _location.value?.residents?.map { it.substringAfter("character/") }
                    ?: emptyList()
            println("allLocationResidentsId $allLocationResidentsId")
            viewModelScope.launch {
                _residents.value =
                    appService.getAllPersonagesByIds(allLocationResidentsId) ?: emptyList()
            }
        }
    }
}