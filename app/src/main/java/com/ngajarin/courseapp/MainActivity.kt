package com.ngajarin.courseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ngajarin.courseapp.model.Course
import com.ngajarin.courseapp.utils.JsonParser

class MainActivity : AppCompatActivity() {
    //init variable
    private lateinit var rvCourse: RecyclerView
    private var list: ArrayList<Course> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getting recycler view id from layout
        rvCourse = findViewById(R.id.rv_data_course)
        //recycler view size optimizer automatically
        rvCourse.setHasFixedSize(true)

        //getting json data into string
        val rawJsonData = JsonParser().getJsonAsset(this)
        //parse json string to list Course object
        val courseData = JsonParser().fromJsonToObject(rawJsonData,this)
        //insert all list course object from json parser to list
        if (courseData != null) {
            list.addAll(courseData)
        }

        //method to show recycler view
        showRecyclerList()

        val toolbar: Toolbar? = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.toolbar_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(item.itemId == R.id.btn_profile){
            val moveToProfileIntent = Intent(this@MainActivity, ProfileActivity::class.java)
            startActivity(moveToProfileIntent)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    //to show recycler view
    private fun showRecyclerList(){
        rvCourse.layoutManager = LinearLayoutManager(this)
        val listCourseAdapter = ListAdapter(list)
        rvCourse.adapter = listCourseAdapter

        listCourseAdapter.setOnClickCallback(object : ListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Course) {
                //init intent
                val moveToDetailIntent = Intent(this@MainActivity, DetailActivity::class.java)

                //put extra data to intent and send to DetailActivity
                moveToDetailIntent.putExtra(DetailActivity.EXTRA_COURSE_NAME, data.course_name)
                moveToDetailIntent.putExtra(DetailActivity.EXTRA_COURSE_PRICE, data.course_price)
                moveToDetailIntent.putExtra(DetailActivity.EXTRA_COURSE_TIME, data.course_est_time)
                moveToDetailIntent.putExtra(DetailActivity.EXTRA_COURSE_MODUL, data.course_total_modul)
                moveToDetailIntent.putExtra(DetailActivity.EXTRA_COURSE_DESC, data.course_desc)
                moveToDetailIntent.putExtra(DetailActivity.EXTRA_COURSE_LEVEL, data.course_level)
                moveToDetailIntent.putExtra(DetailActivity.EXTRA_COURSE_IMG, data.course_img)
                moveToDetailIntent.putExtra(DetailActivity.EXTRA_COURSE_RATE, data.course_rate)

                //move activity to DetailActivity
                startActivity(moveToDetailIntent)
            }
        })
    }

}