package GetDish

import comment.CommentController
import comment.CommentModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureGetCommentRouting() {
    routing {
        get("/getComments"){
            val idDish = call.request.queryParameters["idDish"]
            val offset = call.request.queryParameters["offset"]!!.toLong()
            if (idDish==null)
                call.respond(HttpStatusCode.BadRequest, "not idDish")
            val comments = CommentModel.getComments(idDish?.toIntOrNull()!!, offset)
            if (comments.isEmpty())
                call.respond(HttpStatusCode.NoContent, comments) //"There are no comments"
            else
                call.respond(HttpStatusCode.OK, comments)
        }
        post("/addNewComment"){
            val commentController = CommentController(call)
            commentController.addNewComment()
        }

    }
}