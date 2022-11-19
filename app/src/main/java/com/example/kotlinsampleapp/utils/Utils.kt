package com.example.kotlinsampleapp.utils

import android.content.Context
import java.io.IOException

class Utils {
    fun getJson(context: Context, file: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(file).bufferedReader().use{ it.readText() }
        } catch (exception: IOException) {
            exception.printStackTrace()
            return  null
        }
        return  jsonString
    }
}