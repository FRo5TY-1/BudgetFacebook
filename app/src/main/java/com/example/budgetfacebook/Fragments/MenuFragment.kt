package com.example.budgetfacebook.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.budgetfacebook.ChangePasswordActivity
import com.example.budgetfacebook.LoginActivity
import com.example.budgetfacebook.R
import com.google.firebase.auth.FirebaseAuth

class MenuFragment : Fragment(R.layout.fragment_menu) {
    private lateinit var buttonChangePass: Button
    private lateinit var buttonLogOut: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonChangePass = view.findViewById(R.id.buttonChangePassword)
        buttonLogOut = view.findViewById(R.id.buttonLogOut)

        buttonChangePass.setOnClickListener {
            activity?.let{
                val intent = Intent (it, ChangePasswordActivity::class.java)
                it.startActivity(intent)
            }
        }
        buttonLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            activity?.let{
                val intent = Intent (it, LoginActivity::class.java)
                it.startActivity(intent)
            }
            activity?.finish()
        }
    }
}