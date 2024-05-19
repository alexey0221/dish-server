package userRating

import dish.DishModel
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object UserRatingModel: Table("user_rating") {
    private val userName = UserRatingModel.varchar("user_name",10)
    private val idDish = UserRatingModel.integer("id_dish")
    private val userRating = UserRatingModel.float("user_rating")

    fun setNewUserRating(userRatingDTO: UserRatingDTO){
        transaction {
            val notUserRating = UserRatingModel.select {
                (userName eq userRatingDTO.userName) and
                        (idDish eq userRatingDTO.idDish)
            }.map {  }.isEmpty()
            if (notUserRating) {
                UserRatingModel.insert {
                    it[userName] = userRatingDTO.userName
                    it[idDish] = userRatingDTO.idDish
                    it[userRating] = userRatingDTO.userRating
                }
                DishModel.ratingUpdate(userRatingDTO.idDish, userRatingDTO.userRating)
            }
            else{ // дописать изменение рейтинга пользователем
                val oldUserRating = UserRatingModel.updateUserRating(userRatingDTO)
                DishModel.ratingReUpdate(
                    oldUserRating,
                    userRatingDTO.idDish,
                    userRatingDTO.userRating
                )
            }
        }
    }
    fun getUserRating(idDish: Int, userName: String):Float{
        var userIsNotRate: Boolean = false
        var currentUserRating: Float = 0f
        transaction {
            userIsNotRate = UserRatingModel.select {
                (UserRatingModel.userName eq userName ) and
                        (UserRatingModel.idDish eq idDish) }
                .map { it[UserRatingModel.userRating] }.isEmpty()

            if (!userIsNotRate)
                currentUserRating = UserRatingModel
                    .select {
                        (UserRatingModel.userName eq userName ) and
                                (UserRatingModel.idDish eq idDish)
                    }
                    .map { it[UserRatingModel.userRating] }
                    .single()
        }
        return currentUserRating
    }
    fun updateUserRating(userRatingDTO: UserRatingDTO):Float{
        val oldUserRating = transaction {
            UserRatingModel.select { (UserRatingModel.userName eq userRatingDTO.userName) and
                    (UserRatingModel.idDish eq userRatingDTO.idDish)  }
                .map { it[UserRatingModel.userRating] }.single()
        }
        transaction {
            UserRatingModel.update({
                (UserRatingModel.userName eq userRatingDTO.userName) and
                        (UserRatingModel.idDish eq userRatingDTO.idDish) }){
                it[UserRatingModel.userRating] = userRatingDTO.userRating
            }
        }
        return oldUserRating
    }
}