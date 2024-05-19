package comment

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object CommentModel: Table("comment") {
    private val idComment = CommentModel.integer("id_comment")
    private val userName = CommentModel.varchar("user_name", 15)
    private val textOfComment = CommentModel.text("text")
    private val idDish = CommentModel.integer("id_dish")

    fun insert(commentInsertDTO: CommentInsertDTO){// подумать!!!!!
        val lastID = getLastId()
        println(lastID)
        transaction {
            insert {
                it[userName]=commentInsertDTO.userName
                it[textOfComment]=commentInsertDTO.textOfComment
                it[idDish]=commentInsertDTO.idDish
                it[idComment]=lastID+1
            }
        }
    }
    private fun getLastId():Int{
        var lastId:Int=0
        transaction {
            val commentModel = CommentModel.selectAll().lastOrNull()!!
            lastId=commentModel[idComment]
        }
        return lastId
    }

    fun getComments(idDish: Int, offset:Long=0): List<CommentDTO> {
        val data = mutableListOf<CommentDTO>()
        transaction {
            val commentModel = CommentModel.select {CommentModel.idDish eq idDish }.limit(10, offset).toList()
            for (row in commentModel) {
                val comment = CommentDTO(
                    textOfComment = row[CommentModel.textOfComment],
                    userName = row[CommentModel.userName]
                )
                data.add(comment)
            }
        }
        return data
    }
}