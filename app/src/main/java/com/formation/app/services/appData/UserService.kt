package com.formation.app.services.appData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.formation.app.common.api.AppService
import com.formation.app.models.Episode
import com.formation.app.models.Location
import com.formation.app.models.Personage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(
    private val appService: AppService
) {

    enum class AuthState {
        UnSplash,
        Splash
    }

    private val _authState: MutableLiveData<AuthState> = MutableLiveData(AuthState.Splash)
    val authState: LiveData<AuthState> = _authState

    val personages: MutableStateFlow<List<Personage>> = MutableStateFlow(emptyList())
    val locations: MutableStateFlow<List<Location>> = MutableStateFlow(emptyList())
    val episodes: MutableStateFlow<List<Episode>> = MutableStateFlow(emptyList())

    suspend fun fetchSplashData() {
        delay(2000)
        _authState.postValue(AuthState.UnSplash)
        personages.value = appService.fetchPersonages() ?: emptyList()
        locations.value = appService.fetchLocations() ?: emptyList()
        episodes.value = appService.fetchEpisodes() ?: emptyList()
    }
}
