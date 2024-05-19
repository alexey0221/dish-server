package comment

import kotlinx.serialization.Serializable

@Serializable
data class CommentInsertDTO(
    val textOfComment: String,
    val idDish:Int,
    val userName:String
)
