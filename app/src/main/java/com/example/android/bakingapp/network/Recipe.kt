package com.example.android.bakingapp.network

data class Recipe(
    val name: String,
    val ingredients: Ingredient,
    val steps: Step,
    val servings: Int,
    val image: String
)

data class Ingredient(
    val  quantity: Double,
    val measure: String,
    val ingredient: String
)

data class Step(
    val id: Int,
    val shortDescription: String,
    val description: String,
    val videoURL: String,
    val thumbnailURL: String
)