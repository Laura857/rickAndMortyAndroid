package com.formation.app.common.api

import com.formation.app.models.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object {
        var apiService: ApiService? = null
        fun getInstance(): ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }

    //Character
    @GET("character")
    suspend fun getAllPersonages(): PersonagesResults

    @GET("character/{ids}")
    suspend fun getAllPersonagesByIds(@Path("ids") ids: List<String>): List<Personage>

    @GET("character/{id}")
    suspend fun getPersonageById(@Path("id") id: String): Personage

    //Location
    @GET("location")
    suspend fun getAllLocations(): LocationsResults

    @GET("location/{id}")
    suspend fun getLocationById(@Path("id") id: String): Location

    //Episode
    @GET("episode")
    suspend fun getAllEpisodes(): EpisodesResults

    @GET("episode/{ids}")
    suspend fun getAllEpisodesByIds(@Path("ids") ids: List<String>): List<Episode>

    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: String): Episode
}