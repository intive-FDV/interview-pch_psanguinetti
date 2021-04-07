package com.challenge.myfirstmillion.data.remote.model

import com.challenge.myfirstmillion.data.local.model.UserDB

data class User(
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