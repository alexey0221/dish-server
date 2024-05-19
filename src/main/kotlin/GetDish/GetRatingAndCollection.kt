package GetDish

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import users.UserModel.dishInCollection
import userRating.UserRatingModel.getUserRating

fun Application.configureGetRatingAndCollectionRouting() {
    routing {
        get("/getRatingAndCollection"){
            val idDish = call.request.queryParameters["idDish"]!!.toInt()
            val userName = call.request.queryParameters["userName"]!!
            val userRatingCollection = UserRatingCollection(
                dishInCollection(idDish, userName),//filmInCollection,
                getUserRating(idDish, userName)//currentUserRating
            )
            call.respond(HttpStatusCode.OK, userRatingCollection)
        }
    }
}
@Serializable
data class UserRatingCollection(
    val dishInCollection :Boolean,
    val currentUserRating: Float
)