package users

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object UserModel:Table("users") {

    private val userName = UserModel.varchar("user_name", 10)
    private val email = UserModel.varchar("email", 20)
    private val passwd = UserModel.varchar("passwd", 512)
    private val collection = UserModel.registerColumn<Array<Int>>("collection", IntegerArrayType())

    fun insert(userDTO: UserDTO){
        transaction {
            insert {
                it[userName]=userDTO.userName
                it[email]=userDTO.email
                it[passwd]=userDTO.passwd
            }
        }
    }
    fun fetchUser(nameUser:String):Boolean {
        return transaction {
            UserModel.select {
                (UserModel.userName eq nameUser)
            }
                .map { }
                .isNotEmpty()
        }
    }
    fun fetchEmail(email:String):Boolean {
        return transaction {
            UserModel.select {
                (UserModel.email eq email)
            }
                .map { }
                .isEmpty()
        }
    }
    fun getUserCollection(userName: String):Array<Int>? {
        return try {
            transaction {
                UserModel.select { UserModel.userName eq userName }.map { it[UserModel.collection] }.single()
            }
        }
        catch (e: Exception){
            null
        }
    }

    fun addDishToCollection(userName:String, idDish: Int){
        transaction {
            val collection = UserModel.select { UserModel.userName eq userName }.map { it[UserModel.collection] }.firstOrNull()// .single()

            if (collection != null) {
                val newCollection = collection.plus(idDish)
                UserModel.update({ UserModel.userName eq userName }) {
                    it[UserModel.collection] = newCollection
                }
            }
            else {
                val newCollection: Array<Int> = arrayOf(idDish)
                UserModel.update({UserModel.userName eq userName} ){
                    it[UserModel.collection] = newCollection
                }
            }
        }
    }

    fun delDishFromCollection(userName:String, idDish: Int){
        transaction {
            val collection = UserModel.select { UserModel.userName eq userName }.map { it[UserModel.collection] }.single()
            val newCollection = collection.filter { it != idDish }.toTypedArray()
            UserModel.update({UserModel.userName eq userName} ){
                it[UserModel.collection] = newCollection
            }
        }
    }

    fun dishInCollection(idDish: Int, userName: String):Boolean{
        var filmInCollection = false
        transaction {
            val collection = UserModel.select { UserModel.userName eq userName }
                .map { it[UserModel.collection] }?.single()
            if (collection == null){
                filmInCollection = false
            }
            else {
                filmInCollection = collection.contains(idDish)
            }
        }
        return filmInCollection
    }
    fun findUser(email: String):Boolean {
        return transaction {
            UserModel.select {
                UserModel.email eq email
            }
                .map { it[UserModel.email] }
                .isNotEmpty()
        }
    }
    fun passwordComparison(email: String, passwd:String):String?{
        return transaction {
            UserModel.select { (UserModel.email eq email) and
                    (UserModel.passwd eq passwd)}
                .map { it[UserModel.userName] }.firstOrNull()
        }
    }
}