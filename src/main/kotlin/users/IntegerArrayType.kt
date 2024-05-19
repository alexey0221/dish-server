package users

import org.postgresql.util.PGobject
import org.jetbrains.exposed.sql.ColumnType

class IntegerArrayType : ColumnType() {
    override fun sqlType(): String = "int4[]"

    override fun valueFromDB(value: Any): Any = when (value) {
        is java.sql.Array -> value.array
        else -> super.valueFromDB(value)
    }

    override fun notNullValueToDB(value: Any): Any = when (value) {
        is Array<*> -> {
            val pgObject = PGobject()
            pgObject.type =

                "int4[]"
            pgObject.value = "{" + value.joinToString(",") + "}"
            pgObject
        }
        else -> super.notNullValueToDB(value)
    }
}
