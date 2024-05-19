package dish

import kotlinx.serialization.Serializable

@Serializable
data class Dish(
    val idDish: Int,
    val nameDish: String,
    val rating: Float,
    val recipe: String,
    val ingredients: String,
    val photo: String,
    val tags: String,
    val numberOfRatings: Int
)
