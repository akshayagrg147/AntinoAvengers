package com.antino.avengers.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.antino.avengers.Others.PreferenceUtils
import com.antino.avengers.R
import com.antino.avengers.Utils.gone
import com.antino.avengers.Utils.visible
import com.antino.avengers.databinding.ActivityMainBinding
import com.antino.avengers.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.avengers.gone()
        val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.dialog_slide_up)
        val slideIn: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)
        binding.logoImage.animation = shake
        Handler(Looper.getMainLooper()).postDelayed({
            binding.avengers.visible()
            binding.avengers.animation = slideIn

        }, 650)

        Handler(Looper.getMainLooper()).postDelayed({
            val loginResponse = PreferenceUtils.getLogin()
            if(loginResponse != null) {
                if(loginResponse.name?.isNotEmpty() == true){
                    openHomeActivity()
                } else {
                    openLoginActivity()
                }
            } else {
                openLoginActivity()
            }
        }, 1700)

    }
    private fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun openHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}