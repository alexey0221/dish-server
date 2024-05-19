package comment

import kotlinx.serialization.Serializable

@Serializable
data class CommentDTO(
    val textOfComment: String,
//    val idDish:Int,
    val userName:String
)
