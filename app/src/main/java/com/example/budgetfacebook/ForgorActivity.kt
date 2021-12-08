package com.example.budgetfacebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class ForgorActivity : AppCompatActivity() {
    private lateinit var editTextEmailReset: EditText
    private lateinit var buttonSendReset: Button
    private lateinit var textViewReset: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgor)

        init()
        registerListeners()
    }

    private fun init(){
        editTextEmailReset = findViewById(R.id.editTextEmailReset)
        buttonSendReset = findViewById(R.id.buttonSendReset)
        textViewReset = findViewById(R.id.textViewReset)
    }

    private fun registerListeners() {

        buttonSendReset.setOnClickListener {
            val email = editTextEmailReset.text.toString()

            if (emailValidate(email)) {
                textViewReset.text = ""

                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
            }
        }
    }

    private fun emailValidate(email: String): Boolean {
        return if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textViewReset.text = ""
            true
        } else {
            textViewReset.text = "Please Enter A Valid E-mail"
            false
        }
    }

}