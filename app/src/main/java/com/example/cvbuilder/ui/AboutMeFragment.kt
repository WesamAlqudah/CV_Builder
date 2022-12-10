package com.example.cvbuilder.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cvbuilder.R
import com.example.cvbuilder.models.User
import com.example.cvbuilder.ui.adapters.ListAdapter


class AboutMeFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_about_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUser(view)
    }

    private fun bindUser(view: View) {
        user?.let{
            view.findViewById<TextView>(R.id.tv_aboutMe).text= "${it.aboutMe}"
            setUpEduList(view,it)
            setUpCerList(view,it)
        }

    }

    private fun setUpEduList(view: View, user: User) {
        val adapter= ListAdapter()
        val rv_list=view.findViewById<RecyclerView>(R.id.rv_educationList)
        adapter.setAdapterType(ListAdapter.EDUCATION_TYPE)
        adapter.addEducations(user.educationList!!)
        rv_list.adapter=adapter
    }
    private fun setUpCerList(view: View, user: User) {
        val adapter= ListAdapter()
        val rv_list=view.findViewById<RecyclerView>(R.id.rv_certificationList)
        adapter.setAdapterType(ListAdapter.CERTIFICATION_TYPE)
        adapter.addCertifications(user.certificationList!!)
        rv_list.adapter=adapter
    }

    companion object {
        private const val USER = "user"

        @JvmStatic
        fun newInstance(user: User) =
            AboutMeFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(USER,user)
                }
            }
    }
}