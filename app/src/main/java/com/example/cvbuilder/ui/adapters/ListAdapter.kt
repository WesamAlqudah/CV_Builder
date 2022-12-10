package com.example.cvbuilder.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cvbuilder.R
import com.example.cvbuilder.models.Certification
import com.example.cvbuilder.models.Education
import com.example.cvbuilder.models.Experience

class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var experiencesList: List<Experience>
    private lateinit var educationList: List<Education>
    private lateinit var cerList: List<Certification>

    private var type = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (type) {
            EXPERIENCE_TYPE -> ExperiencesItemVM(
                inflater.inflate(
                    R.layout.item_experience,
                    parent,
                    false
                )
            )
            EDUCATION_TYPE -> EducationItemVM(
                inflater.inflate(
                    R.layout.item_education,
                    parent,
                    false
                )
            )
            else -> CertificationItemVM(
                inflater.inflate(
                    R.layout.item_certification,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (type) {
            EXPERIENCE_TYPE ->
                experiencesList.let {
                    (holder as ExperiencesItemVM).bind(it[position])
                }
            EDUCATION_TYPE ->
                educationList.let {
                    (holder as EducationItemVM).bind(it[position])
                }
            else ->
                cerList.let {
                    (holder as CertificationItemVM).bind(it[position])
                }
        }
    }

    override fun getItemCount(): Int {
        return when (type) {
            EXPERIENCE_TYPE -> experiencesList.size
            EDUCATION_TYPE -> educationList.size
            else -> cerList.size
        }
    }

    fun setAdapterType(type: Int) {
        this.type = type
    }

    fun addExperiences(experiencesList: List<Experience>) {
        this.experiencesList = experiencesList
        notifyDataSetChanged()
    }

    fun addEducations(educationList: List<Education>) {
        this.educationList = educationList
        notifyDataSetChanged()
    }

    fun addCertifications(cerList: List<Certification>) {
        this.cerList = cerList
        notifyDataSetChanged()
    }

    companion object {
        val EXPERIENCE_TYPE = 1
        val EDUCATION_TYPE = 2
        val CERTIFICATION_TYPE = 3
    }

    inner class ExperiencesItemVM(val item: View) : RecyclerView.ViewHolder(item) {
        fun bind(experience: Experience) {
            item.findViewById<ImageView>(R.id.img_comp_logo)
                .setImageResource(experience.imageUrl)
            item.findViewById<TextView>(R.id.tv_item_job_title)
                .text=experience.jobTitle
            item.findViewById<TextView>(R.id.tv_item_comp_title)
                .text=experience.company
            item.findViewById<TextView>(R.id.tv_item_job_period_data)
                .text=experience.date
            item.findViewById<TextView>(R.id.tv_item_job_location)
                .text=experience.location
            item.findViewById<TextView>(R.id.tv_item_job_des)
                .text=experience.description

        }
    }

    inner class EducationItemVM(val item: View) : RecyclerView.ViewHolder(item) {
        fun bind(education: Education) {
            item.findViewById<ImageView>(R.id.img_edu_logo)
                .setImageResource(education.imageUrl)
            item.findViewById<TextView>(R.id.tv_edu_school)
                .text=education.school
            item.findViewById<TextView>(R.id.tv_edu_degree)
                .text=education.degree
        }
    }

    inner class CertificationItemVM(val item: View) : RecyclerView.ViewHolder(item) {
        fun bind(certification: Certification) {
            item.findViewById<ImageView>(R.id.img_cer_logo)
                .setImageResource(certification.imageUrl)
            item.findViewById<TextView>(R.id.tv_cer_name)
                .text=certification.name+"("+certification.year+")"

        }
    }
}