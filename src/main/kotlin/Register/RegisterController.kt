package Register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import users.UserDTO
import users.UserModel
import users.UserModel.fetchEmail

class RegisterController(private val call: ApplicationCall) {

    private fun validateEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
        return emailRegex.matches(email)
    }
    suspend fun registerNewUser() {
        try {
            val userName = call.request.queryParameters["name"]!!
            val email = call.request.queryParameters["email"]!!
            val passwd = call.request.queryParameters["passwd"]!!
        } catch (e: Exception) {
            println("No parametr")
        }

        val registerReceiveRemote = RegisterReceiveRemote(
            userName = call.request.queryParameters["name"]!!,
            email = call.request.queryParameters["email"]!!,
            passwd = call.request.queryParameters["passwd"]!!
        )

        if (fetchEmail(registerReceiveRemote.email)) {
            if (registerReceiveRemote.userName.length<=10) {
                if (UserModel.fetchUser(registerReceiveRemote.userName)) {
                    call.respond(
                        HttpStatusCode.NoContent,
                        "Такой пользователь уже существует, придумайте что-нибудь еще ;)"
                    )
                } else {
                    if (!validateEmail(registerReceiveRemote.email))
                        call.respond(HttpStatusCode.NoContent, "Почта введена неправильно")
                    else {
                        UserModel.insert(
                            UserDTO(
                                userName = registerReceiveRemote.userName,
                                email = registerReceiveRemote.email,
                                passwd = registerReceiveRemote.passwd
                            )
                        )
                        call.respond(HttpStatusCode.OK, "Вы успешно зарегистрировались")
                    }
                }
            }
            else
                call.respond(HttpStatusCode.NoContent, "Ваше имя слишком длинное")
        } else
            call.respond(HttpStatusCode.NoContent, "К этой почте уже привязан аккаунт")
    }
}