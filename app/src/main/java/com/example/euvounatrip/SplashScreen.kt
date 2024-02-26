package com.example.euvounatrip

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.euvounatrip.MainActivity
import com.example.euvounatrip.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private val SPLASH_TIME: Long = 4000
    private var binding: ActivitySplashScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding!!.logo.alpha = 0f // Define a opacidade inicial para 0
        // Define uma animação de fade in
        binding!!.logo.animate().alpha(1f).duration = 3000 // Ajuste a duração conforme necessário

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME)
    }
}