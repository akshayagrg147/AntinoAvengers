package com.antino.avengers.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.antino.avengers.LoginViewModel
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
            val loginRequest = LoginRequest(
                binding.acEmail.text.toString(),
                binding.edtPassword.text.toString(),
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
            Toast.makeText(this, Gson().toJson(it), Toast.LENGTH_SHORT).show()
        }
    }
}