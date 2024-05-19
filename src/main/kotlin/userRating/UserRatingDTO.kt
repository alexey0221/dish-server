package userRating

import kotlinx.serialization.Serializable

@Serializable
data class UserRatingDTO(
    val userRating: Float,
    val userName: String,
    val idDish: Int
)
