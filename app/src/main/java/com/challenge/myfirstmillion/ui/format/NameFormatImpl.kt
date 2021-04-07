package com.challenge.myfirstmillion.ui.format

import javax.inject.Inject

class NameFormatImpl @Inject constructor() : NameFormat {
    //Format example: S. Jobs
    override fun getNameFormatted(name: String, lastName: String): String {
        return "${name.take(1)}. ${lastName}"
    }
}