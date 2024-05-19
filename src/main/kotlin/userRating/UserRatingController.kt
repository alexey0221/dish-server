package userRating

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import userRating.UserRatingModel.setNewUserRating
import userRating.UserRatingModel.updateUserRating
import dish.DishModel.ratingReUpdate
class UserRatingController(private val call: ApplicationCall) {
    private val newUserRating = UserRatingDTO(
        idDish = call.request.queryParameters["idDish"]!!.toInt(),
        userName = call.request.queryParameters["userName"]!!.toString(),
        userRating = call.request.queryParameters["userRating"]!!.toFloat()
    )
    suspend fun setNewRating(){
        setNewUserRating(newUserRating)
        call.respond(HttpStatusCode.OK,"Successfully!!")
    }
    suspend fun updatingRating(){
        val oldUserRating = updateUserRating(newUserRating)
        ratingReUpdate(
            oldUserRating,
            newUserRating.idDish,
            newUserRating.userRating)
    }
}