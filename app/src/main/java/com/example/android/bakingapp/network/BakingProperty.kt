package com.example.android.bakingapp.network

data class BakingProperty(
    val id: Int,
    val name: String,
    val ingredients: List<BakingIngredients>,
    val steps: List<BakingSteps>,
    val servings: Int,
    val image: String
)

data class BakingIngredients(val  quantity: Double, val measure: String, val ingredient: String)
data class BakingSteps(val id: Int, val shortDescription: String, val description: String,
                       val videoURL: String, val thumbnailURL: String)