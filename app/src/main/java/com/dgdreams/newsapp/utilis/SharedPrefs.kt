package com.dgdreams.newsapp.utilis

import android.app.Application
import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SharedPrefs @Inject constructor( private val context: Application) {


        fun saveUpdatedDat(){

            var dateTime: String

            var calendar: Calendar = Calendar.getInstance()
            var simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
            dateTime = simpleDateFormat.format(calendar.time).toString()
            val sharedPref = context.getSharedPreferences(
                "updatedDate", Context.MODE_PRIVATE)
            sharedPref.edit().putString("updatedDate",dateTime).commit()

        }

    fun getUpdatedTime(){
        val sharedPref = context.getSharedPreferences(
            "updatedDate", Context.MODE_PRIVATE)
         Toast.makeText(context, sharedPref.getString("updatedDate", "not updated yet"), Toast.LENGTH_LONG).show()

    }

}