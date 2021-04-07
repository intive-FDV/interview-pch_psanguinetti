package com.challenge.myfirstmillion.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.challenge.myfirstmillion.data.local.model.UserDB
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var peopleDao: PeopleDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        peopleDao = db.peopleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insert_and_read() {
        //GIVEN
        val user = UserDB("1", "Steve", "Jobs", "none", 100)

        //WHEN
        peopleDao.insert(user)
        val userList = peopleDao.getPeople(10, 0)

        //THEN
        assertThat(userList.get(0), equalTo(user))
    }

    @Test
    fun insert_without_updating() {
        //GIVEN
        val user1 = UserDB("1", "Steve", "Jobs", "none", 100)
        val user2 = UserDB("1", "Steve", "Jobs", "none", 120)
        val user3 = UserDB("2", "Peter", "Parker", "none", 150)

        //WHEN
        peopleDao.insert(user1)
        peopleDao.insert(user2)
        peopleDao.insert(user3)

        val userList = peopleDao.getPeople(10, 0)

        //THEN
        assertThat(userList.get(0), equalTo(user1))
        assertThat(userList.get(1), equalTo(user3))
    }

    @Test
    fun insertall_without_updating() {
        //GIVEN
        val user1 = UserDB("1", "Steve", "Jobs", "none", 100)
        val user2 = UserDB("1", "Steve", "Jobs", "none", 120)
        val user3 = UserDB("2", "Peter", "Parker", "none", 150)

        val list = listOf(user1,user2,user3)

        //WHEN
        peopleDao.insertAll(list)
        val userList = peopleDao.getPeople(10, 0)

        //THEN
        assertThat(userList.get(0), equalTo(user1))
        assertThat(userList.get(1), equalTo(user3))
    }

    @Test
    fun delete_user() {
        //GIVEN
        val user1 = UserDB("1", "Steve", "Jobs", "none", 100)

        //WHEN
        peopleDao.insert(user1)
        peopleDao.delete(user1)
        val userList = peopleDao.getPeople(10, 0)

        //THEN
        assertEquals(userList.size, 0)
    }

    @Test
    fun update() {
        //GIVEN
        val user1 = UserDB("1", "Steve", "Jobs", "none", 100)
        val newHourlyWage = 120

        //WHEN
        peopleDao.insert(user1)
        peopleDao.update(user1.id, 120)
        val userList = peopleDao.getPeople(10, 0)

        //THEN
        assertEquals(userList.get(0).hourlyWage, newHourlyWage)
    }
}