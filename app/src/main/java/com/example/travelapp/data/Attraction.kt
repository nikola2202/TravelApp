package com.example.travelapp.data

data class Attraction(
    val description: String = "",
    val facts: List<String> = listOf(),
    val id: String = "",
    val image_urls: List<String> = listOf(),
    val location: Location = Location(),
    val months_to_visit: String = "",
    val title: String = ""
)