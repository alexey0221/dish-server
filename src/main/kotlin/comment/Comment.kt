package comment

import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val idComment: Int,
    val textOfComment: String,
    val idDish:Int,
    val userName:String
)
