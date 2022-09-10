package com.antino.avengers.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.antino.avengers.LoginViewModel
import com.antino.avengers.Others.PreferenceUtils
import com.antino.avengers.Utils.common.PREF_LOGGED_IN_USER
import com.antino.avengers.data.pojo.loginApi.request.LoginRequest
import com.antino.avengers.databinding.ActivityLoginBinding
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModel<LoginViewModel<Any?>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginButton.setOnClickListener {
            callLoginActivity()
        }

        setUpObserver()
    }

    private fun callLoginActivity() {
        //if (isNetworkAvailable()) {
        if (!Patterns.EMAIL_ADDRESS.matcher( binding.etEmail.text.toString()).matches()){
            Toast.makeText(this, "Enter correct email id", Toast.LENGTH_SHORT).show()
            return
        }
        val loginRequest = LoginRequest(
            binding.etEmail .text.toString(),
            binding.etpassword .text.toString(),
            )

            loginViewModel.loginApi(
                loginRequest,
                "",

            )

        //}
        /*else
            noNetworkToast()*/
    }

    private fun setUpObserver() {
        loginViewModel.dataAddUser.observe(this){
            Log.d("LoginActivity", Gson().toJson(it))
            if(it.status == 200) {
                PreferenceUtils.putObject(it, PREF_LOGGED_IN_USER)

                intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()

            }

        }
    }


}