package com.example.cvbuilder.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.cvbuilder.R
import com.example.cvbuilder.models.User

class ContactFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUser(view)
    }

    private fun bindUser(view: View) {
        user?.let {
            view.findViewById<TextView>(R.id.tv_title_mobile)
                .text=it.phone
            view.findViewById<TextView>(R.id.tv_title_gmail)
                .text=it.email
            view.findViewById<TextView>(R.id.tv_title_linkedIn)
                .text=it.linkedIn
            view.findViewById<TextView>(R.id.tv_title_github)
                .text=it.github
            view.findViewById<TextView>(R.id.tv_title_resume)
                .text=it.pdf
        }
    }

    companion object {
        private const val USER = "user"

        @JvmStatic
        fun newInstance(user: User) =
            ContactFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(USER,user)
                }
            }
    }

}