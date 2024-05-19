package userRating

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureUserRating(){

    routing {
        post("/setNewUserRating"){
            val userRatingController = UserRatingController(call)
            userRatingController.setNewRating()
        }
    }
}