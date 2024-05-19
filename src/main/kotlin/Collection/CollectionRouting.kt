package Collection

import dish.DishDTO
import dish.DishModel.returnUserCollectionOfDishes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import users.UserModel.addDishToCollection
import users.UserModel.delDishFromCollection
import users.UserModel.getUserCollection

fun Application.configureCollectionRouting() {
    routing {
        get("/getCollection"){
            val userName = call.request.queryParameters["userName"]!!.toString()
            val collection = getUserCollection(userName)
            if (collection!=null) {
                val filmsDTO = returnUserCollectionOfDishes(collection)
                call.respond(HttpStatusCode.OK, filmsDTO)
            }
            else
                call.respond(HttpStatusCode.NoContent, listOf<DishDTO>())
        }
        post("/addFilmToCollection"){
            val userName = call.request.queryParameters["userName"]!!.toString()
            val idDish = call.request.queryParameters["idDish"]!!.toInt()
            addDishToCollection(userName, idDish)
            call.respond(HttpStatusCode.OK, "Dish add to collection")
        }
        post("/delFilmFromCollection"){
            val userName = call.request.queryParameters["userName"]!!.toString()
            val idDish = call.request.queryParameters["idDish"]!!.toInt()
            delDishFromCollection(userName, idDish)
            call.respond(HttpStatusCode.OK, "Dish del from collection")
        }
    }
}