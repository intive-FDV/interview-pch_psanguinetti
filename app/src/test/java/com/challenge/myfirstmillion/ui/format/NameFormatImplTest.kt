package com.challenge.myfirstmillion.ui.format

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class NameFormatImplTest {

    private lateinit var formatter: NameFormat

    @Before
    fun setUp() {
        formatter = NameFormatImpl()
    }

    @Test
    fun first_name_initial_followed_by_the_last_name() {

        //GIVEN
        val name = "Steve"
        val lastName = "Jobs"

        //WHEN
        val formattedName = formatter.getNameFormatted(name, lastName)

        //THEN
        //Format example: S. Jobs
        assertEquals("The name's format is incorrect", "S. Jobs", formattedName)
    }

    @Test
    fun first_name_initial_followed_by_the_last_name_fail() {

        //GIVEN
        val name = "Steve"
        val lastName = "Jobs"

        //WHEN
        val formattedName = formatter.getNameFormatted(name, lastName)

        //THEN
        //Format example: S. Jobs
        assertNotEquals("The name's format is incorrect", "Steve Jobs", formattedName)
    }
}