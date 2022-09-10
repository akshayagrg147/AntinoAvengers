package com.antino.avengers.presentation.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.antino.avengers.LoginViewModel
import com.antino.avengers.Others.PreferenceUtils
import com.antino.avengers.Utils.common.PREF_LOGGED_IN_USER
import com.antino.avengers.Utils.isNetworkAvailable
import com.antino.avengers.Utils.noNetworkToast
import com.antino.avengers.Utils.toast
import com.antino.avengers.data.pojo.getReviewsApi.GetReviewsRequest
import com.antino.avengers.data.pojo.loginApi.request.LoginRequest
import com.antino.avengers.databinding.ActivityLoginBinding
import com.antino.avengers.presentation.ViewModel.DeveloperViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModel<LoginViewModel<Any?>>()
    private var day = 0
    private var month: Int = 0
    private var year: Int = 0
    private lateinit var mcalendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mcalendar = Calendar.getInstance()
        day = mcalendar.get(Calendar.DAY_OF_MONTH)
        year = mcalendar.get(Calendar.YEAR)
        month = mcalendar.get(Calendar.MONTH)
        binding.loginButton.setOnClickListener {
            callLoginActivity()
            //openCalender()
        }

        setUpObserver()
    }

    private fun callLoginActivity() {
        if (isNetworkAvailable()) {
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

        }
        else
            noNetworkToast()
    }

    private fun setUpObserver() {
        loginViewModel.dataAddUser.observe(this) {
            Log.d("LoginActivity", Gson().toJson(it))
//            try {
                if (it.status == 200) {

                    PreferenceUtils.putObject(it, PREF_LOGGED_IN_USER)
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()


                }

//            } catch(e:Exception) {
//                Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
//            }
        }
    }

    private fun putLoginPref() {
        val loginPref = PreferenceUtils.getLogin()
        Toast.makeText(this, Gson().toJson(loginPref), Toast.LENGTH_SHORT).show()
    }

    private fun openCalender() {
        val listener =
            DatePickerDialog.OnDateSetListener {
                    _, year, monthOfYear, dayOfMonth,
                ->
                binding.etEmail.setText("$dayOfMonth/${monthOfYear+1}/$year")
            }
        val dpDialog = DatePickerDialog(this, listener, year, month, day)
        dpDialog.show()
    }

}
