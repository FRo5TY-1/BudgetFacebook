package com.example.budgetfacebook.viewpagerfragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.budgetfacebook.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FragmentInformation : Fragment(R.layout.viewpager_fragment_one) {
    private lateinit var userInfoTextView: TextView
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("UserInfo")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userInfoTextView = view.findViewById(R.id.personalInformation)

        init()
    }

    private fun init() {

        db.child(auth.currentUser?.uid!!).get().addOnSuccessListener {
            if (it.exists()) {
                val firstName = it.child("firstName").value
                val lastName = it.child("lastName").value
                val country = it.child("country").value
                val number = it.child("number").value
                val birthYear = it.child("birthYear").value

                userInfoTextView.text = " First Name: $firstName " +
                        "\nLast Name: $lastName " +
                        "\nCountry of residence: $country " +
                        "\nPhone Number: $number " +
                        "\nBirth Year: $birthYear"
            }
        }

    }
}