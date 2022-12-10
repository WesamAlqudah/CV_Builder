package com.example.cvbuilder.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.cvbuilder.R
import de.hdodenhof.circleimageview.CircleImageView
import com.example.cvbuilder.common.Constrains
import com.example.cvbuilder.models.User


class HomeFragment() : Fragment() {
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getSerializable(USER) as User
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUser(view)
    }

    private fun bindUser(view: View) {
        user?.let {
            view.findViewById<CircleImageView>(R.id.img_personal).setImageResource(it.image_url!!)
            view.findViewById<ImageView>(R.id.img_cover).setImageResource(it.image_url!!)
            view.findViewById<TextView>(R.id.tv_welcome).text= "${it.fName} ${it.lName}"
            view.findViewById<TextView>(R.id.tv_job_title).text= "${it.jobTitle}"
            view.findViewById<TextView>(R.id.tv_career_note).text= "${it.careerNote}"
            it.workExperience?.let {
                if (it.containsKey(Constrains.languages))
                    view.findViewById<TextView>(R.id.tv_languages).text= "${it[Constrains.languages]}"
                if (it.containsKey(Constrains.frameworks_Web))
                    view.findViewById<TextView>(R.id.tv_framework).text= "${it[Constrains.frameworks_Web]}"
                if (it.containsKey(Constrains.Microservices_cloud))
                    view.findViewById<TextView>(R.id.tv_microservices).text= "${it[Constrains.Microservices_cloud]}"
                if (it.containsKey(Constrains.databases))
                    view.findViewById<TextView>(R.id.tv_databases).text= "${it[Constrains.databases]}"
                if (it.containsKey(Constrains.tools))
                    view.findViewById<TextView>(R.id.tv_tools).text= "${it[Constrains.tools]}"
            }
        }
    }

    companion object {
        private const val USER = "user"


        @JvmStatic
        fun newInstance(user: User) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(USER,user)
                }
            }
    }


}