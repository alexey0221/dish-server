package GetDish

import dish.DishDTO
import dish.DishModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.nio.charset.Charset

fun Application.configureGetDishRouting() {
    routing {
        get("/getAllDishesRatingDESC") {
            val offset = call.request.queryParameters["offset"]!!.toLong()
            val dishDTO = DishModel.getDishesSortedByRatingDESC(offset)
            call.respond(dishDTO)
        }
        get("/getAllDishesRatingASC") {
            val offset = call.request.queryParameters["offset"]!!.toLong()
            val dishDTO = DishModel.getDishesSortedByRatingASC(offset)
            call.respond(dishDTO)
        }
        get("/getAllDishesNameDESC") {
            val offset = call.request.queryParameters["offset"]!!.toLong()
            val dishDTO = DishModel.getDishesSortedByNameDESС(offset)
            call.respond(dishDTO)
        }
        get("/getAllDishesNameASC") {
            val offset = call.request.queryParameters["offset"]!!.toLong()
            val dishDTO = DishModel.getDishesSortedByNameASC(offset)
            call.respond(dishDTO)
        }
        get("/getDish") {
            val idDish = call.request.queryParameters["idDish"]
            if (idDish==null)
                call.respond(HttpStatusCode.BadRequest, "not idFilm")
            val dishDTO = DishModel.findIdDish(idDish?.toIntOrNull()!!)
            call.respond(dishDTO)
        }
        get("/findDishesByTag") {
            val tag = call.request.queryParameters["tag"]
            if (tag == null) {
                call.respond(HttpStatusCode.NoContent, "The tag was not found")
            }
            else {
                val listTags = tag.split(",")
                val  data = mutableListOf<DishDTO>()
                for (tagIn in listTags){
                    val dishDTO = DishModel.findDishesByTag(tagIn)
                    data.addAll(dishDTO)
                }

                call.respond(HttpStatusCode.OK, data)
            }
        }
        get("/findDishByName"){
            val name = call.request.queryParameters["name"]
//            println(name)
            if (name == null) {
                call.respond(HttpStatusCode.NotFound, "The name was not found")
            }
            var newName = String(name.toString().toByteArray(
                Charset.forName("utf-8")), Charset.forName("utf-8") )
//            println(newName)
            newName = newName.plus("%")
            val dishDTO = DishModel.findDish(newName)
            if (dishDTO == null)
                call.respond(HttpStatusCode.NoContent, "The dish was not found")
            else
                call.respond(HttpStatusCode.OK, dishDTO)
        }

    }
}