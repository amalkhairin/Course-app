package com.ngajarin.courseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    //constant
    companion object {
        const val EXTRA_COURSE_NAME = "extra_course_name"
        const val EXTRA_COURSE_LEVEL = "extra_course_level"
        const val EXTRA_COURSE_DESC = "extra_course_desc"
        const val EXTRA_COURSE_RATE = "extra_course_rate"
        const val EXTRA_COURSE_PRICE = "extra_course_price"
        const val EXTRA_COURSE_MODUL = "extra_course_modul"
        const val EXTRA_COURSE_TIME = "extra_course_time"
        const val EXTRA_COURSE_IMG = "extra_course_img"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //getting view
        val imgCourse: ImageView = findViewById(R.id.img_detail_course)
        val tvCourseName: TextView = findViewById(R.id.tv_course_detail_name)
        val tvCourseLevel: TextView = findViewById(R.id.tv_course_detail_level)
        val tvCourseRate: TextView = findViewById(R.id.tv_course_detail_rate)
        val tvCourseDesc: TextView = findViewById(R.id.tv_course_detail_desc)
        val tvCourseModul: TextView = findViewById(R.id.tv_course_detail_modul)
        val tvCourseTime: TextView = findViewById(R.id.tv_course_detail_time)
        val tvCoursePrice: TextView = findViewById(R.id.tv_course_detail_price)

        val btnEnroll: Button = findViewById(R.id.btn_enroll)

        //getting data from intent
        val courseName = intent.getStringExtra(EXTRA_COURSE_NAME)
        val courseLevel = intent.getStringExtra(EXTRA_COURSE_LEVEL)
        val courseImage = intent.getIntExtra(EXTRA_COURSE_IMG, 0)
        val courseRate = intent.getDoubleExtra(EXTRA_COURSE_RATE, 0.0)
        val courseDesc = intent.getStringExtra(EXTRA_COURSE_DESC)
        val courseModul = intent.getIntExtra(EXTRA_COURSE_MODUL, 0)
        val courseTime = intent.getIntExtra(EXTRA_COURSE_TIME, 0)
        val coursePrice = intent.getIntExtra(EXTRA_COURSE_PRICE, 0)

        //set view with new value
        imgCourse.setImageResource(courseImage)
        tvCourseName.text = courseName
        tvCourseLevel.text = courseLevel
        tvCourseRate.text = courseRate.toString()
        tvCourseDesc.text = courseDesc
        tvCourseModul.text = courseModul.toString()
        tvCourseTime.text = "$courseTime Jam"
        tvCoursePrice.text = if(coursePrice == 0) "Free" else "Rp.$coursePrice"

        //set listener to enroll button
        btnEnroll.setOnClickListener(this)

        //create action button on toolbar with back button
        startActionMode(callback)
    }

    override fun onClick(v: View?) {
        if(v != null){
            if(v.id == R.id.btn_enroll){
                //make toast when button clicked
                Toast.makeText(this,"success: Enrolled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val callback = object: ActionMode.Callback{
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem): Boolean {
            return false
        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.title = "Detail"
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        //when back button pressed, finish this activity
        override fun onDestroyActionMode(mode: ActionMode?) {
            finish()
        }

    }
}