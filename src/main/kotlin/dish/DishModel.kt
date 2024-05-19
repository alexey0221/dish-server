package dish

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File
import java.util.*
import kotlin.math.roundToInt

object DishModel: Table("dish") {
    private val idDish = DishModel.integer("id_dish")
    private val nameDish = DishModel.varchar("name_dish", 40)
    private val ingredients = DishModel.varchar("ingredients", 500)
    private val recipe = DishModel.text("recipe")
    private val tags = DishModel.varchar("tags", 10)
    private val rating = DishModel.float("rating")
    private val photo = DishModel.varchar("photo", 100)
    private val numberOfRatings = DishModel.integer("number_of_ratings")

    fun getDishesSortedByRatingDESC (offset: Long = 0):List<DishDTO>{
        var data = mutableListOf<DishDTO>()
        transaction {
            val dishModel = DishModel.select(Op.TRUE)// { FilmModel.nameFilm.eq(nameFilm) }
                .orderBy(DishModel.rating to SortOrder.DESC)
                .limit(10, offset)
                .toList()
            data = creatingList(dishModel)
        }
        return data

    }
    fun getDishesSortedByRatingASC (offset: Long = 0):List<DishDTO>{
        var data = mutableListOf<DishDTO>()
        transaction {
            val dishModel = DishModel.select(Op.TRUE)// { FilmModel.nameFilm.eq(nameFilm) }
                .orderBy(DishModel.rating to SortOrder.ASC)
                .limit(10, offset)
                .toList()
            data = creatingList(dishModel)
        }
        return data

    }
    fun getDishesSortedByNameDESС (offset: Long = 0):List<DishDTO>{
        var data = mutableListOf<DishDTO>()
        transaction {
            val dishModel = DishModel.select(Op.TRUE)// { FilmModel.nameFilm.eq(nameFilm) }
                .orderBy(DishModel.nameDish to SortOrder.DESC)
                .limit(10, offset)
                .toList()
            data = creatingList(dishModel)
        }
        return data

    }
    fun getDishesSortedByNameASC (offset: Long = 0):List<DishDTO>{
        var data = mutableListOf<DishDTO>()
        transaction {
            val dishModel = DishModel.select(Op.TRUE)// { FilmModel.nameFilm.eq(nameFilm) }
                .orderBy(DishModel.nameDish to SortOrder.ASC)
                .limit(10, offset)
                .toList()
            data = creatingList(dishModel)
        }
        return data

    }
    fun findDish (nameDish: String):List<DishDTO>?{
        var data = mutableListOf<DishDTO>()
        val dish = try {
            transaction {
                val dishModel = DishModel.select {
                    DishModel.nameDish.like(capitalizeFirstLetter(nameDish))
                }
                    .orderBy(DishModel.nameDish to SortOrder.ASC)
                    .toList()
                data = creatingList(dishModel)
            }
        }
        catch (e: Exception){ null }

        if (dish != null) { return data }

        return null
    }
    fun capitalizeFirstLetter(input: String): String {
        return input.substring(0, 1).uppercase() + input.substring(1)
    }
    fun findIdDish (idDish: Int):DishDTO {
        return transaction {
            val filmModel = DishModel.select { DishModel.idDish eq idDish }.single()
            val imageFile = File(filmModel[DishModel.photo])
            val imageBytes = imageFile.readBytes()
            val base64Image = Base64.getEncoder().encodeToString(imageBytes)
            val roundedRating = (filmModel[DishModel.rating].toDouble() * 100.0).roundToInt() / 100.0.toFloat()
            DishDTO(
                idDish = filmModel[DishModel.idDish],
                nameDish = filmModel[DishModel.nameDish],
                rating = roundedRating,
                ingredients = filmModel[DishModel.ingredients],
                recipe = filmModel[DishModel.recipe],
                tags = filmModel[DishModel.tags],
                photo = base64Image
            )
        }
    }
    fun findDishesByTag (style: String):List<DishDTO>{
        var data = mutableListOf<DishDTO>()// listOf<FilmDTO>()
        transaction {
            val dishModel = DishModel.select { DishModel.tags.like(style) }.toList() //.where(FilmModel.styles.like("%триллер%"))
            data = creatingList(dishModel)
        }
        return data
    }

    fun ratingUpdate(idDish: Int, addRating: Float){
        var numTotalRatings: Int = 0 // количество оценивших
        var avgRating = 0f// текущий рейтинг

        transaction {
            avgRating = DishModel.select { DishModel.idDish eq idDish }
                .map { it[DishModel.rating] }.single()
            numTotalRatings = DishModel.select { DishModel.idDish eq idDish }
                .map { it[DishModel.numberOfRatings]}.single()
        }
        // Обновляем общий рейтинг и количество голосов для дальнейших расчетов
        numTotalRatings += 1
        val updatedRating = (avgRating*(numTotalRatings-1)+addRating)/numTotalRatings
        transaction {
            // Здесь выполняем запрос к базе данных для обновления рейтинга для конкретного фильма
            DishModel.update({ DishModel.idDish eq idDish }) {
                it[DishModel.rating] = updatedRating
                it[DishModel.numberOfRatings] = numTotalRatings
            }
        }
    }

    fun ratingReUpdate(oldUserRating:Float ,idDish: Int, newRating: Float){
        var numTotalRatings: Int = 0 // количество оценивших
        var avgRating = 0f// текущий рейтинг

        transaction {
            avgRating = DishModel.select { DishModel.idDish eq idDish }
                .map { it[DishModel.rating] }.single()
            numTotalRatings = DishModel.select { DishModel.idDish eq idDish }
                .map { it[DishModel.numberOfRatings]}.single()
        }
        val updatedRating = (avgRating*numTotalRatings-oldUserRating+newRating)/numTotalRatings//(avgRating*(numTotalRatings-1)+addRating)/numTotalRatings//(numTotalRatings * avgRating + k * newRating) / (numTotalRatings + k)
        transaction {
            // Здесь выполняем запрос к базе данных для обновления рейтинга для конкретного фильма
            DishModel.update({ DishModel.idDish eq idDish }) {
                it[DishModel.rating] = updatedRating
            }
        }
    }

    fun returnUserCollectionOfDishes(idDishes: Array<Int>):List<DishDTO>{
        val data = mutableListOf<DishDTO>()
        transaction {
            for (id in idDishes) {
                val filmModel = DishModel.select { DishModel.idDish eq id }.single()
                val imageFile = File(filmModel[DishModel.photo])
                val imageBytes = imageFile.readBytes()
                val base64Image = Base64.getEncoder().encodeToString(imageBytes)
                val roundedRating = (filmModel[DishModel.rating].toDouble() * 100.0).roundToInt() / 100.0.toFloat()
                val dish = DishDTO(
                    idDish = filmModel[DishModel.idDish],
                    nameDish = filmModel[DishModel.nameDish],
                    rating = roundedRating,
                    recipe = filmModel[DishModel.recipe],
                    ingredients = filmModel[DishModel.ingredients],
                    tags = filmModel[DishModel.tags],
                    photo = base64Image
                )
                data.add(dish)
            }
        }
        return data
    }


    private fun creatingList(filmModel:List<ResultRow>):MutableList<DishDTO>{
        val data = mutableListOf<DishDTO>()// listOf<FilmDTO>()
        for (row in filmModel) {
            val imageFile = File(row[DishModel.photo])
            val imageBytes = imageFile.readBytes()
            val base64Image = Base64.getEncoder().encodeToString(imageBytes)
            val roundedRating = (row[DishModel.rating].toDouble() * 100.0).roundToInt() / 100.0.toFloat()
            val dish = DishDTO(
                idDish = row[DishModel.idDish],
                nameDish = row[DishModel.nameDish],
                rating = roundedRating,
                tags = row[DishModel.tags],
                photo = base64Image,
                ingredients = row[DishModel.ingredients],
                recipe = row[DishModel.recipe]
            )
            data.add(dish)
        }
        return data
    }
}