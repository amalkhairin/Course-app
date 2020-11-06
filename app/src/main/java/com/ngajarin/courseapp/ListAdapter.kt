package com.ngajarin.courseapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ngajarin.courseapp.model.Course

class ListAdapter(private val listCourse: ArrayList<Course>): RecyclerView.Adapter<ListAdapter.CardViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvCourseName: TextView = itemView.findViewById(R.id.tv_course_name)
        var tvCourseLevel: TextView = itemView.findViewById(R.id.tv_course_level)
        var tvCourseRate: TextView = itemView.findViewById(R.id.tv_course_rate)
        var tvCoursePrice: TextView = itemView.findViewById(R.id.tv_course_price)
        var imgCourse: ImageView = itemView.findViewById(R.id.img_course)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Course)
    }

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_course, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCourse.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val course = listCourse[position]

        //set image view with course image
        Glide.with(holder.itemView.context)
            .load(course.course_img)
            .apply(RequestOptions().override(150,150))
            .into(holder.imgCourse)

        //set textView with course properties
        holder.tvCourseName.text = course.course_name
        holder.tvCourseLevel.text = course.course_level
        holder.tvCourseRate.text = course.course_rate.toString()
        holder.tvCoursePrice.text = if(course.course_price == 0) "Free" else "Rp."+course.course_price.toString()

        //set on click listener to item view
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listCourse[holder.adapterPosition]) }
    }
}