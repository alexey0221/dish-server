package comment

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

class CommentController(private val call: ApplicationCall) {
    private val commentDTO = CommentInsertDTO(
        idDish = call.request.queryParameters["idDish"]!!.toInt(),
        textOfComment= call.request.queryParameters["textOfComment"]!!.toString(),
        userName = call.request.queryParameters["userName"]!!.toString()
    )

    suspend fun addNewComment(){
        CommentModel.insert(commentDTO)
        call.respond(HttpStatusCode.OK, "New comment added")
    }
}