package com.challenge.myfirstmillion.data.local

import androidx.room.*
import com.challenge.myfirstmillion.data.local.model.UserDB


@Dao
interface PeopleDao {
    @Query("SELECT * FROM UserDB ORDER BY hourlyWage ASC LIMIT :limit OFFSET :offset")
    fun getPeople(limit: Int, offset: Int): List<UserDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(users: List<UserDB>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: UserDB)

    @Delete
    fun delete(user: UserDB)

    @Query("UPDATE UserDB SET hourlyWage = :hourlyWage WHERE id = :userId")
    fun update(userId: String, hourlyWage: Int)
}