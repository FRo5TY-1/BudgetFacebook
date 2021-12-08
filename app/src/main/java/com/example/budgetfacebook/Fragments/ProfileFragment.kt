package com.example.budgetfacebook.Fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.budgetfacebook.R

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val personName = ProfileFragmentArgs.fromBundle(requireArguments()).personName

        if (personName.isNotEmpty()) {
            var personProfile = "This is your Profile, $personName!"
            view.findViewById<TextView>(R.id.textView).text = personProfile
        }
    }
}
