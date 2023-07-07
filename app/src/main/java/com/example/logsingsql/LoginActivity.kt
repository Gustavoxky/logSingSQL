package com.example.logsingsql

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.logsingsql.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    var binding: ActivityLoginBinding? = null
    var dataBaseHelper: DataBaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        dataBaseHelper = DataBaseHelper(this)
        binding!!.loginButton.setOnClickListener {
            val email = binding!!.loginEmail.text.toString()
            val password = binding!!.loginPassword.text.toString()
            if (email == "" || password == "") Toast.makeText(
                this@LoginActivity,
                "All fields are mandatory",
                Toast.LENGTH_SHORT
            ).show() else {
                val checkCredentials = dataBaseHelper!!.checkEmailPassword(email, password)
                if (checkCredentials == true) {
                    Toast.makeText(this@LoginActivity, "Login successfully", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "invalid Credentials", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        binding!!.signupRedirectText.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}