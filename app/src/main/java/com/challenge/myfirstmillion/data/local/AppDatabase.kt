package com.challenge.myfirstmillion.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.challenge.myfirstmillion.data.local.model.UserDB

@Database(entities = arrayOf(UserDB::class), version = 8)
abstract class AppDatabase : RoomDatabase() {
    abstract fun peopleDao(): PeopleDao
}
