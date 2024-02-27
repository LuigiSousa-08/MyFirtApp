package com.example.euvounatrip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.euvounatrip.databinding.ActivityLoginScreenBinding
import com.example.euvounatrip.databinding.ActivityRegisterScreenBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegisterScreen : AppCompatActivity() {
    private var binding: ActivityRegisterScreenBinding? = null
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        acessaLogin()

        auth = Firebase.auth

        binding?.btnRegistrar?.setOnClickListener {
            val email: String = binding?.txtEditEmail?.text.toString()
            val password: String = binding?.txtEditPassword?.text.toString()
            val confirmPassword: String = binding?.txtEditConfirmPassword?.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    createUserWithEmailAndPassword(email, password)
                } else {
                    Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, preencha corretamente", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createUserWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "createUserWithEmailAndPassword:Sucess")
                //val user = auth.currentUser
            } else {
                Log.w(TAG, "createUserWithEmailAndPassword:Failure", task.exception)
                Toast.makeText(baseContext, "Authentication Failure", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private var TAG = "EmailAndPassword"
    }


    fun acessaLogin() {
        binding?.navegaLogin?.setOnClickListener {
            startActivity(Intent(this, LoginScreen::class.java))
        }
    }
}