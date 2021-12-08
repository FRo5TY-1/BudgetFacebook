package com.example.budgetfacebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var editTextNewPassword1: EditText
    private lateinit var editTextNewPassword2: EditText
    private lateinit var textView4: TextView
    private lateinit var textView5: TextView
    private lateinit var buttonNewPass: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        init()
        registerListeners()
    }

    private fun init(){
        editTextNewPassword1 = findViewById(R.id.editTextNewPassword)
        editTextNewPassword2 = findViewById(R.id.editTextNewPassword1)
        textView4 = findViewById(R.id.textView4)
        textView5 = findViewById(R.id.textView5)
        buttonNewPass = findViewById(R.id.buttonNewPass)
    }

    private fun registerListeners() {
        val password1 = editTextNewPassword1.text.toString()
        val password2 = editTextNewPassword2.text.toString()

        buttonNewPass.setOnClickListener {

            if (passwordValidate(password1, password2 )) {
                textView4.text = ""
                textView5.text = ""


                FirebaseAuth.getInstance().currentUser?.updatePassword(password1)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                    }
            }
        }
    }

    private fun passwordValidate(password1: String, password2: String): Boolean {

        when {

            password1.length < 9 -> {
                textView4.text = "Password Has To Be At Least 9 Characters Long"
                return false
            }
            !password1.matches(".*[A-Z].*".toRegex()) -> {
                textView4.text = "Password Must Contain 1 Upper-case Character"
                return false
            }
            !password1.matches(".*[a-z].*".toRegex()) -> {
                textView4.text = "Password Must Contain 1 Lower-case Character"
                return false
            }
            !password1.matches(".*[!@#$%^&*+=/?].*".toRegex()) -> {
                textView4.text = "Password Must Contain 1 Symbol"
                return false
            }
            password1 != password2 -> {
                textView5.text = "Passwords Don't Match"
                return false
            }
            else -> return true
        }
    }

}