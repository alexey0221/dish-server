package users

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val userName:String,
    val email:String,
    val passwd: String
)
