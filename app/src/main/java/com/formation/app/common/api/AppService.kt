package com.formation.app.common.api

import com.formation.app.models.Episode
import com.formation.app.models.Location
import com.formation.app.models.Personage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppService @Inject constructor() {

    var apiService: ApiService = ApiService.getInstance()

    suspend fun fetchPersonages(): List<Personage>? {
        return callApiNoCatch {
            apiService.getAllPersonages().results
        }
    }

    suspend fun getAllPersonagesByIds(ids: List<String>): List<Personage>? {
        return callApiNoCatch {
            apiService.getAllPersonagesByIds(ids)
        }
    }

    suspend fun getPersonageById(id: String): Personage? {
        return callApiNoCatch {
            apiService.getPersonageById(id)
        }
    }

    suspend fun fetchLocations(): List<Location>? {
        return callApiNoCatch {
            apiService.getAllLocations().results
        }
    }

    suspend fun getLocationById(id: String): Location? {
        return callApiNoCatch {
            apiService.getLocationById(id)
        }
    }

    suspend fun fetchEpisodes(): List<Episode>? {
        return callApiNoCatch {
            apiService.getAllEpisodes().results
        }
    }

    suspend fun getAllEpisodesByIds(ids: List<String>): List<Episode>? {
        return callApiNoCatch {
            apiService.getAllEpisodesByIds(ids)
        }
    }

    suspend fun getEpisodeById(id: String): Episode? {
        return callApiNoCatch {
            apiService.getEpisodeById(id)
        }
    }
}