package Authorization

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import users.UserModel.findUser
import users.UserModel.passwordComparison

fun Application.configureAuthorizationRouting(){
    routing{
        get("/authorization"){
            val email = call.request.queryParameters["email"]!!
            val passwd = call.request.queryParameters["passwd"]!!
            if (findUser(email)) {
                val user = passwordComparison(email, passwd)
                if ( user != null)
                    call.respond(HttpStatusCode.OK, user)
                else
                    call.respond(HttpStatusCode.NoContent, "Не правильный пароль")
            }
            else
                call.respond(HttpStatusCode.NoContent, "Пользователя с такой почтой не существует")
        }
    }
}