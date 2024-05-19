package users

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userName: String,
    val email: String,
    val passwd: String,
    val collection: Array<Int>
)
