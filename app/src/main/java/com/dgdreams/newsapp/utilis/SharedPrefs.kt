package com.dgdreams.newsapp.utilis

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.dgdreams.newsapp.data.api.ApiService
import com.dgdreams.newsapp.ui.MainViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SharedPrefs @Inject constructor( private val context: Application) {


        fun saveUpdatedDat(){

            var dateTime: String
            var calendar: Calendar
            var simpleDateFormat: SimpleDateFormat

            calendar = Calendar.getInstance()
            simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
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