package com.example.budgetfacebook.Fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.budgetfacebook.R

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var editTextName: EditText
    private lateinit var buttonSend: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = Navigation.findNavController(view)

        editTextName = view.findViewById(R.id.editTextName)
        buttonSend = view.findViewById(R.id.buttonSend)

        buttonSend.setOnClickListener {

            var personName = editTextName.text.toString()
            if (personName.isEmpty())
                return@setOnClickListener

            val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment(personName)

            controller.navigate(action)
        }
    }
}