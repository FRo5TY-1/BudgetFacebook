package com.example.budgetfacebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.budgetfacebook.databinding.ActivityEditProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("UserInfo")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        focusListeners()
        binding.buttonFinish.setOnClickListener {
            if (dataLoad()) {
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
            }
        }
    }

    private fun dataLoad(): Boolean {
        val firstNameValidate = binding.firstNameContainer.helperText == null
        val lastNameValidate = binding.lastNameContainer.helperText == null

        if (firstNameValidate && lastNameValidate) {
            val firstName = binding.firstNameEditText.text.toString()
            val lastName = binding.lastNameEditText.text.toString()
            var pictureUrl = binding.editTextProfilePicture.text.toString()
            var country = binding.editTextCountry.text.toString()
            var phoneNumber = binding.editTextNumber.text.toString()
            var birthYear = binding.editTextBirthYear.text.toString()

            if (pictureUrl.isEmpty()) {
                pictureUrl = ""
            }
            if (country.isEmpty()) {
                country = "Unknown"
            }
            if (phoneNumber.isEmpty()) {
                phoneNumber = "Unknown"
            }
            if (birthYear.isEmpty()) {
                birthYear = "Unknown"
            }

            val userInfo = UserInfo(firstName, lastName, pictureUrl, country, phoneNumber, birthYear)
            db.child(auth.currentUser?.uid!!).setValue(userInfo)

            return true
        }
        return false
    }

    private fun focusListeners() {
        binding.firstNameEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.firstNameContainer.helperText = firstNameValidate()
            }
        }
        binding.lastNameEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.lastNameContainer.helperText = lastNameValidate()
            }
        }
    }

    private fun firstNameValidate() : String? {
        val firstName = binding.firstNameEditText.text.toString()
        if (firstName.length < 3) {
            return "Must Be At Least 3 Characters Long"
        }
        return null
    }
    private fun lastNameValidate() : String? {
        val lastName = binding.lastNameEditText.text.toString()

        if (lastName.length < 3) {
            return "Must Be At Least 3 Characters Long"
        }
        return null
    }

}