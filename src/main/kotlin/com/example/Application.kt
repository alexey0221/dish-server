package com.example

import Authorization.configureAuthorizationRouting
import Collection.configureCollectionRouting
import GetDish.configureGetCommentRouting
import GetDish.configureGetDishRouting
import GetDish.configureGetRatingAndCollectionRouting
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database
import Register.configureRegisterRouting
import userRating.configureUserRating

fun main() {
    embeddedServer(CIO, port = 8080, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    try {
        Database.connect(
            "jdbc:postgresql://localhost:5432/ck_recipe",
            "org.postgresql.Driver",
            "postgres",
            "sdnm5577"
        )
    }
    catch (e:Exception){
        println("Database is not connection")
    }
    configureSerialization()
//    configureDatabases()
    configureRouting()
    configureRegisterRouting()
    configureUserRating()
    configureAuthorizationRouting()
    configureGetCommentRouting()
    configureGetDishRouting()
    configureCollectionRouting()
    configureGetRatingAndCollectionRouting()
}
