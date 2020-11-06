package com.ngajarin.courseapp.utils

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.ngajarin.courseapp.R
import com.ngajarin.courseapp.model.Course
import org.json.JSONObject
import java.io.InputStream
import java.lang.Exception

class JsonParser {
    fun fromJsonToObject(data: String?, context: Context): ArrayList<Course>?{
        //init array list of Course object
        val list = ArrayList<Course>()

        try {
            //create json object from data string
            val jsonObj: JSONObject = JSONObject(data)

            //getting "data" node from json object
            val courseData: JSONObject = jsonObj.getJSONObject("data")

            //iteration from 1 represent id of data in json object
            for(i in 1..courseData.length()){
                //create temporary course object
                val tempData = Course()

                //getting json node with id i
                val dataId = courseData.getJSONObject("$i")

                //set temp object properties with data from json object
                tempData.course_name = dataId.getString("course_name")
                tempData.course_desc = dataId.getString("course_desc")
                tempData.course_rate = dataId.getDouble("course_rate")
                tempData.course_level = dataId.getString("course_level")
                tempData.course_price = dataId.getInt("course_price")
                tempData.course_total_modul = dataId.getInt("course_modul_total")
                tempData.course_est_time = dataId.getInt("course_est_time")

                //getting image name from json
                val imgRes = dataId.getString("course_img")
                //getting resource id with image name
                val imgResId = context.resources.getIdentifier(imgRes, "drawable", context.packageName)
                //set temp course_img property with resource id
                tempData.course_img = imgResId

                //insert temp object to list
                list.add(tempData)
            }
        } catch (e:Exception){
            Log.e(TAG,"${e.stackTrace}")
            println(e.message)
            return null
        }
        return list
    }

    //method to get json object into a string
    fun getJsonAsset(context: Context):String?{
        //init local variable
        val jsonStr: String

        try {
            //open json file from raw directory
            val inputStream: InputStream = context.resources.openRawResource(R.raw.data_course)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.use {
                it.read(buffer)
            }

            //convert json object to string
            jsonStr = String(buffer)
        } catch(e: Exception) {
            Log.e("error","${e.stackTrace}")
            return null
        }
        return jsonStr
    }
}