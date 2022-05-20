package com.formation.app.models

data class Location (
    val id : String,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>
)