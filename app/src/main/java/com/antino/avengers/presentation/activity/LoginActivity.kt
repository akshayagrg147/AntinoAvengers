package com.antino.avengers.presentation.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.antino.avengers.LoginViewModel
import com.antino.avengers.R
import com.antino.avengers.databinding.ActivityLoginBinding
import com.bitla.ts.domain.pojo.login_model.request.LoginRequest
import com.bitla.ts.domain.pojo.login_model.request.ReqBody
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModel<LoginViewModel<Any?>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.acEmail.setText("Enter email")
        binding.loginButton.setOnClickListener {
            Toast.makeText(this,"Aa",Toast.LENGTH_LONG).show()
        }

        callLoginActivity()
        setUpObserver()
    }

    private fun callLoginActivity() {
        //if (isNetworkAvailable()) {
            val reqBody = ReqBody(
                "",
                "",
                "",
                "",
            )
            val loginRequest =
                LoginRequest("", "", "", reqBody)

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
        }
    }
}