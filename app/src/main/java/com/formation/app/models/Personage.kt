package com.formation.app.models

data class Personage(
    val id: String,
    val name: String,
    val species: String,
    val gender: String,
    val image: String,
    val status: String,
    val episode: List<String>,
    val location: PersonageLocation,
    val origin: Origin
)