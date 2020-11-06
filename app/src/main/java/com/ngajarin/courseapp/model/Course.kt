package com.ngajarin.courseapp.model

data class Course (
    var course_name: String = "",
    var course_desc: String = "",
    var course_rate: Double = 0.0,
    var course_level: String = "",
    var course_price: Int = 0,
    var course_total_modul: Int = 0,
    var course_est_time: Int = 0,
    var course_img: Int = 0
)