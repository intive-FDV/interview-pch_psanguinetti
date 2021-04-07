package com.challenge.myfirstmillion.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.challenge.myfirstmillion.data.remote.model.User

@Entity
data class UserDB(
    @PrimaryKey
    val id: String,
    val name: String,
    val lastName: String,
    val picture: String,
    val hourlyWage: Int
) {
    override fun equals(other: Any?): Boolean {
        val otherPerson = other as UserDB
        return this.id == otherPerson.id &&
                this.name == otherPerson.name &&
                this.lastName == otherPerson.lastName &&
                this.picture == otherPerson.picture &&
                this.hourlyWage == otherPerson.hourlyWage
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}


fun User.toUserDB() =
    UserDB(
        id = id,
        name = name,
        lastName = lastName,
        picture = picture,
        hourlyWage = hourlyWage
    )