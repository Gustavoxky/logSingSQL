package com.example.logsingsql

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.logsingsql.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    var binding: ActivitySignupBinding? = null
    var dataBaseHelper: DataBaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        dataBaseHelper = DataBaseHelper(this)
        binding!!.signupButton.setOnClickListener {
            val email = binding!!.signupEmail.text.toString()
            val password = binding!!.signupPassword.text.toString()
            val confirmPassword = binding!!.signupConfirm.text.toString()
            if (email == "" || password == "" || confirmPassword == "") Toast.makeText(
                this@SignupActivity,
                "all fields are mandatory",
                Toast.LENGTH_SHORT
            ).show() else {
                if (password == confirmPassword) {
                    val checkUserEmail = dataBaseHelper!!.checkEmail(email)
                    if (checkUserEmail == false) {
                        val insert = dataBaseHelper!!.insertData(email, password)
                        if (insert == true) {
                            Toast.makeText(
                                this@SignupActivity,
                                "Signup Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@SignupActivity, "Signup Filed", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Toast.makeText(
                            this@SignupActivity,
                            "User already exists, Please login",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(this@SignupActivity, "Invalid password", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        binding!!.loginRedirectText.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}