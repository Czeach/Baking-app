package com.example.android.bakingapp.network

data class Recipes(
    val name: String,
    val ingredients: List<Ingredients>,
    val steps: List<Steps>,
    val servings: Int,
    val image: String
)

data class Ingredients(
    val  quantity: Double,
    val measure: String,
    val ingredient: String
)

data class Steps(
    val id: Int,
    val shortDescription: String,
    val description: String,
    val videoURL: String,
    val thumbnailURL: String
)