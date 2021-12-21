package com.example.budgetfacebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.example.budgetfacebook.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emailFocusListener()
        passwordFocusListener()
        repeatPasswordFocusListener()

        binding.registerButton.setOnClickListener { registration() }
    }


    private fun registration() {
        binding.emailContainer.helperText = emailValidate()
        binding.passwordContainer.helperText = passwordValidate()
        binding.repeatPasswordContainer.helperText = repeatPasswordValidate()

        val emailValid = binding.emailContainer.helperText == null
        val passwordValidate = binding.emailContainer.helperText == null
        val repeatPasswordValidate = binding.repeatPasswordContainer.helperText == null

        if (emailValid && passwordValidate && repeatPasswordValidate) {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this, EditProfileActivity::class.java))
                            finish()
                        }
                    }
        }
    }


    private fun emailFocusListener() {
        binding.emailEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.emailContainer.helperText = emailValidate()
            }
        }
    }


    private fun emailValidate(): String? {
        val emailText = binding.emailEditText.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
            return "Invalid Email Address"
        }
        return null
    }


    private fun passwordFocusListener() {
        binding.passwordEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.passwordContainer.helperText = passwordValidate()
            }
        }
    }


    private fun passwordValidate(): String? {
        val password = binding.passwordEditText.text.toString()
        return when {
            password.length < 9 -> {
                "Password Must Contain 9 Characters"
            }
            !password.matches(".*[A-Z].*".toRegex()) -> {
                "Password Must Contain 1 Upper-case Character"
            }
            !password.matches(".*[a-z].*".toRegex()) -> {
                "Password Must Contain 1 Lower-case Character"
            }
            !password.matches(".*[!@#$%^&*+=/].*".toRegex()) -> {
                "Password Must Contain 1 Special Character (!@#$%^&*+=/)"
            }
            else -> null
        }
    }


    private fun repeatPasswordFocusListener() {
        binding.repeatPasswordEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.repeatPasswordContainer.helperText = repeatPasswordValidate()
            }
        }
    }


    private fun  repeatPasswordValidate(): String? {
        val password = binding.passwordEditText.text.toString()
        val repeatPassword = binding.repeatPasswordEditText.text.toString()

        if (password != repeatPassword) {
            return "Passwords Don't Match"
        }
        return null
    }
}