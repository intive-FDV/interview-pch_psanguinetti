package com.challenge.myfirstmillion.ui.model

import com.challenge.myfirstmillion.data.local.model.UserDB
import com.challenge.myfirstmillion.ui.format.DateFormat
import com.challenge.myfirstmillion.ui.format.NameFormat
import com.challenge.myfirstmillion.ui.format.WageFormat

data class UserUI(
    val id: String,
    val name: String,
    val lastName: String,
    val picture: String,
    val hourlyWage: Int,
    val monthsToBeMillionaire: Int,
    var viewHolderType: Int? = null,
    val nameFormat: NameFormat,
    val wageFormat: WageFormat,
    val dateFormat: DateFormat
) {
    val formatedName: String
    val timeRequired: String
    val wage: String

    init {
        formatedName = nameFormat.getNameFormatted(name, lastName)
        timeRequired = dateFormat.getYearsAndMonths(monthsToBeMillionaire)
        wage = wageFormat.getYearlylWage(hourlyWage)
    }

    override fun equals(other: Any?): Boolean {
        val otherPerson = other as UserUI
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

fun UserDB.toUI(
    nameFormat: NameFormat,
    wageFormat: WageFormat,
    dateFormat: DateFormat,
    monthsToBeMillionaire: Int
) =
    UserUI(
        id = id,
        name = name,
        lastName = lastName,
        picture = picture,
        hourlyWage = hourlyWage,
        monthsToBeMillionaire = monthsToBeMillionaire,
        nameFormat = nameFormat,
        wageFormat = wageFormat,
        dateFormat = dateFormat
    )