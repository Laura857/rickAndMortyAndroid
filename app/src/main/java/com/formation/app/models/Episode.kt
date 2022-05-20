package com.formation.app.models

data class Episode(
    val id: String,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
)